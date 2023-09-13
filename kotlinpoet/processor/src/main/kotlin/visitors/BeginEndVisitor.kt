package dev.kord.codegen.generator.visitors

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.kord.codegen.generator.packageName
import dev.kord.codegen.generator.utils.FileSpec
import dev.kord.codegen.generator.utils.mapToValueParameterList
import dev.kord.codegen.generator.utils.toParameterSpec
import dev.kord.codegen.generator.builder_functions.getBuilderScopeName

/**
 * This visitor detects the following pattern:
 * ```kotlin
 * type.beginX()
 * type.stuff()
 * type.endX()
 * ```
 *
 * and adds an extension that replaces it with
 *
 * ```kotlin
 * type.withX() {
 *   stuff()
 * }
 * ```
 *
 * most notable this wraps `beginControlFlow` functions
 */
object BeginEndVisitor : VisitorBase() {
    private val nameAllocator = NameAllocator()
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: SymbolProcessorEnvironment) {
        classDeclaration.declarations.filterIsInstance<KSClassDeclaration>()
            .forEach { visitClassDeclaration(it, data) }

        val beginFunction =
            classDeclaration.getDeclaredFunctions().firstOrNull { it.simpleName.asString().startsWith("begin") }
                ?: return
        val endFunction =
            classDeclaration.getDeclaredFunctions().firstOrNull { it.simpleName.asString().startsWith("end") }
                ?: run {
                    data.logger.warn("Found begin function without an end function", beginFunction)
                    return
                }

        val name = "with" + beginFunction.simpleName.asString().substringAfter("begin")

        val builderClass = classDeclaration.parentDeclaration as? KSClassDeclaration ?: return
        val fileName =
            nameAllocator.newName(name.replaceFirstChar(Char::uppercase) + builderClass.simpleName.asString())
        val file = FileSpec(data.packageName, fileName) {
            val function = FunSpec.builder(name).apply {
                addParameters(beginFunction.parameters.map(KSValueParameter::toParameterSpec))
                val builderName = builderClass.getBuilderScopeName(data.packageName)
                val builderParameter =
                    ParameterSpec.builder("block", builderName).build()
                addParameter(builderParameter)

                receiver(classDeclaration.toClassName())
                returns(classDeclaration.toClassName())
                val valueParameters = parameters
                    .dropLast(1) // last one is builder
                    .mapToValueParameterList()

                addCode(
                    "return·%L(%L).apply(%N).%L()",
                    beginFunction.simpleName.asString(),
                    valueParameters,
                    builderParameter,
                    endFunction.simpleName.asString()
                )
            }.build()
            addFunction(function)

        }

        file.writeTo(data.codeGenerator, false)
    }
}
