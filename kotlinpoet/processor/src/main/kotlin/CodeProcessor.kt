package dev.kord.codegen.generator

import com.google.devtools.ksp.isPublic
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.squareup.kotlinpoet.KModifier
import dev.kord.codegen.generator.builder_functions.BuilderVisitor
import dev.kord.codegen.generator.reification.ReifyingVisitor
import dev.kord.codegen.generator.reification.TypeVariableName
import dev.kord.codegen.generator.utils.accept
import dev.kord.codegen.generator.visitors.BeginEndVisitor
import dev.kord.codegen.kotlinpoet.FunSpec
import dev.kord.codegen.kotlinpoet.LambdaTypeName
import dev.kord.codegen.kotlinpoet.addCode
import dev.kord.codegen.kotlinpoet.addParameter
import java.util.*

val SymbolProcessorEnvironment.packageName: String
    get() = options["package-name"]!!

private class CodeProcessor private constructor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getNewFiles()
            .onEach { it.accept(environment, ReifyingVisitor) }
            .flatMap(KSFile::declarations)
            .filter(KSDeclaration::isPublic)
            .forEach { file ->
                file.accept(environment, BuilderVisitor, BeginEndVisitor)
            }

        return emptyList() // we don't need to defer symbols
    }

    class Provider : SymbolProcessorProvider {
        override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor = CodeProcessor(environment)
    }
}
