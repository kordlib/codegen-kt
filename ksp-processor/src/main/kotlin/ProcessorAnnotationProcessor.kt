package dev.kord.codegen.ksp.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import dev.kord.codegen.ksp.processor.generator.processAnnotation

class ProcessorAnnotationProcessor private constructor(private val environment: SymbolProcessorEnvironment) :
    SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation(PROCESSOR_ANNOTATION)
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.ANNOTATION_CLASS }
            .forEach {
                environment.processAnnotation(it)
            }
        return emptyList() // we do not need to deffer
    }

    class Provider : SymbolProcessorProvider {
        override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
            ProcessorAnnotationProcessor(environment)
    }
}
