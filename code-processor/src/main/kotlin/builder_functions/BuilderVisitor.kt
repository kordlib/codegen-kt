package dev.kord.codegen.generator.builder_functions

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.kord.codegen.generator.packageName
import dev.kord.codegen.generator.utils.*
import dev.kord.codegen.generator.visitors.VisitorBase
import dev.kord.codegen.ksp.companionDeclaration

/**
 * This detects any time of factory function of a spec.
 *
 * A factory function is a function either named `get`, `builder` or `xBuilder` within the companion
 * object of a type.
 *
 * A `get` factory function, should just get inlined to `specName(parameters)
 * A `builder` function should be wrapped into:
 * ```kotlin
 * SpecName(parameters) {
 * }
 * ```
 *
 * A `xBuilder` function should be inlined as
 * ```kotlin
 * x(parameters) {
 * }
 * ```
 */
object BuilderVisitor : VisitorBase() {
    override fun visitFile(file: KSFile, data: SymbolProcessorEnvironment) {
        file.declarations
            .filterIsInstance<KSClassDeclaration>()
            .forEach { visitClassDeclaration(it, data) }
    }

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: SymbolProcessorEnvironment) {
        val companion = classDeclaration.companionDeclaration ?: return
        val builderFunctions = companion.getDeclaredFunctions()
            .accept(BuilderFunctionCollector, emptyList())
            .flatten()

        val addBuilder = RunOnceFunction<FileSpec.Builder, String> { file, builderName ->
            file.optInForContracts()
            val alias = LambdaTypeName.get(
                classDeclaration.toClassName().nestedClass("Builder"),
                returnType = UNIT
            ).copy(annotations = listOf(AnnotationSpec.builder(CODEGEN_DSL).build()))
            val typeAliasSpec =
                TypeAliasSpec.builder(builderName, alias).build()
            file.members.add(0, typeAliasSpec)
        }

        val fileSpec = FileSpec(data.packageName, "${classDeclaration.simpleName.asString()}Factories") {
            builderFunctions.forEach {
                if (it.hasBuilder) {
                    addBuilder(this, it.getBuilderScopeName(data.packageName).simpleName)
                    addFunction(it.generateBuilderFactoryFunction(data))
                } else {
                    addFunction(it.generateNoBuilderFactoryFunction())
                }
            }
        }
        if (fileSpec.members.isNotEmpty()) {
            fileSpec.writeTo(data.codeGenerator, false)
        }
    }
}
