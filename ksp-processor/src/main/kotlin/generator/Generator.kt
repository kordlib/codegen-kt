package dev.kord.codegen.ksp.processor.generator

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.kord.codegen.ksp.processor.PROCESSOR_ANNOTATION

data class ProcessingContext(
    val environment: SymbolProcessorEnvironment,
    val file: FileSpec.Builder,
    val declaration: KSClassDeclaration,
    val packageName: String
)

@OptIn(DelicateKotlinPoetApi::class)
fun SymbolProcessorEnvironment.processAnnotation(declaration: KSClassDeclaration) {
    val packageName =
        declaration.annotations.first { it.annotationType.resolve().declaration.qualifiedName?.asString() == PROCESSOR_ANNOTATION }
            .arguments.first().value as String
    val fileSpec = FileSpec.builder(packageName, declaration.simpleName.asString()).apply {
        addKotlinDefaultImports(includeJs = false)
        addAnnotation(AnnotationSpec.get(Suppress("DataClassPrivateConstructor")))
        addAliasedImport(declaration.toClassName(), "Annotation")
    }
    val context = ProcessingContext(this, fileSpec, declaration, packageName)
    context.dataClassRepresentation()
    context.accessorFunction()

    fileSpec.build().writeTo(codeGenerator, false)
}
