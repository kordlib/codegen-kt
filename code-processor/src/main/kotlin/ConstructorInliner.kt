package dev.kord.codegen.generator

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFile
import dev.kord.codegen.generator.constructor_inliner.getInlineConstructors
import dev.kord.codegen.generator.constructor_inliner.processInlineConstructors
import dev.kord.codegen.ksp.annotations.InlineConstructor
import dev.kord.codegen.ksp.getSymbolsWithAnnotation

/**
 * Please refer to the documentation of [InlineConstructor].
 */
class ConstructorInliner private constructor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation<InlineConstructor>()
            .forEach {
                val constructors = it.getInlineConstructors()
                environment.processInlineConstructors(
                    resolver,
                    it as KSFile,
                    constructors
                )
            }

        return emptyList()
    }

    class Provider : SymbolProcessorProvider {
        override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor = ConstructorInliner(environment)
    }
}
