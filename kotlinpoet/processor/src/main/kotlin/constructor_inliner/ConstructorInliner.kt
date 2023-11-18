package dev.kord.codegen.generator.constructor_inliner

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.writeTo
import dev.kord.codegen.generator.builder_functions.BuilderFunctionCollector
import dev.kord.codegen.generator.packageName
import dev.kord.codegen.generator.utils.accept

fun SymbolProcessorEnvironment.processInlineConstructors(
    resolver: Resolver,
    file: KSFile,
    constructors: Sequence<InlineConstructor>
) {
    val spec = FileSpec.builder(packageName, file.fileName.dropLast(3)).apply {
        addKotlinDefaultImports(includeJvm = true, includeJs = false)

        constructors.forEach { constructor ->
            val builderClass =
                resolver.getClassDeclarationByName(constructor.forClass.declaration.qualifiedName!!)!!

            builderClass.getDeclaredFunctions()
                .filter { it.simpleName.asString() == constructor.functionName }
                .forEach loop@{
                    val argumentType = it.parameters.first().type.resolve().declaration as KSClassDeclaration
                    val constructorCompanion = argumentType.declarations
                        .filterIsInstance<KSClassDeclaration>()
                        .firstOrNull(KSClassDeclaration::isCompanionObject) ?: return@loop

                    val constructorFunctions = constructorCompanion.getDeclaredFunctions()
                        .accept(BuilderFunctionCollector, emptyList())
                        .flatten()

                    constructorFunctions.forEach { function ->
                        val simpleName = function.simpleName.asString()
                        if (simpleName !in constructor.ignoreBuilders) {
                            addFunction(function.generateInlinedConstructorWithNameParameter(packageName, constructor))
                            if (constructor.nameProperty != null
                                // Sometimes there are overloads without a name property
                                && function.parameters.any { parameter -> parameter.name?.asString() == constructor.nameProperty }) {
                                addFunction(
                                    function.generateInlinedConstructorWithNameDelegate(
                                        packageName,
                                        constructor
                                    )
                                )
                            }
                        }
                    }
                }
        }
    }.build()

    spec.writeTo(codeGenerator, false)
}
