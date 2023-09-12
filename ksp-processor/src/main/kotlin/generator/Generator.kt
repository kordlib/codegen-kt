package dev.kord.codegen.ksp.processor.generator

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.kord.codegen.kotlinpoet.FileSpec
import dev.kord.codegen.ksp.processor.getProcessorAnnotation

data class ProcessingContext(
    val environment: SymbolProcessorEnvironment,
    val file: FileSpec.Builder,
    val declaration: KSClassDeclaration,
    val packageName: String
)

@OptIn(DelicateKotlinPoetApi::class)
fun SymbolProcessorEnvironment.processAnnotation(declaration: KSClassDeclaration) {
    val packageName = declaration.getProcessorAnnotation().packageName
    val fileSpec = FileSpec(packageName, declaration.simpleName.asString()) {
        addKotlinDefaultImports(includeJs = false)
        addAnnotation(AnnotationSpec.get(Suppress("DataClassPrivateConstructor")))
        addAliasedImport(declaration.toClassName(), "Annotation")

        val context = ProcessingContext(this@processAnnotation, this, declaration, packageName)
        context.dataClassRepresentation()
        context.accessorFunction()
    }

    fileSpec.writeTo(codeGenerator, false)
}
