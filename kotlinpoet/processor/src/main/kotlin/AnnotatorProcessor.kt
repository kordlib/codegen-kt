package dev.kord.codegen.generator

import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFile
import dev.kord.codegen.generator.annotator.getAnnotators
import dev.kord.codegen.generator.annotator.processAnnotators
import dev.kord.codegen.ksp.annotations.Annotator
import dev.kord.codegen.ksp.annotations.InlineConstructor
import dev.kord.codegen.ksp.getSymbolsWithAnnotation

/**
 * Please refer to the documentation of [InlineConstructor].
 */
class AnnotatorProcessor private constructor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation<Annotator>()
            .forEach {
                val annotators = it.getAnnotators()
                environment.processAnnotators(resolver, it as KSFile, annotators)
            }

        return emptyList()
    }

    class Provider : SymbolProcessorProvider {
        override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor = AnnotatorProcessor(environment)
    }
}
