package dev.kord.codegen.ksp.processor.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.kord.codegen.kotlinpoet.FileSpec
import dev.kord.codegen.ksp.annotations.NullIfDefault
import dev.kord.codegen.ksp.annotations.OtherIfDefault
import dev.kord.codegen.ksp.processor.getEithers
import dev.kord.codegen.ksp.processor.getOtherIfDefault
import dev.kord.codegen.ksp.processor.getProcessorAnnotation

data class ProcessingContext(
    val environment: SymbolProcessorEnvironment,
    val file: FileSpec.Builder,
    val declaration: KSClassDeclaration,
    val packageName: String
)

private data class Property(
    val index: Int,
    val declaration: KSPropertyDeclaration
) : KSPropertyDeclaration by declaration

@OptIn(DelicateKotlinPoetApi::class, KspExperimental::class)
fun SymbolProcessorEnvironment.processAnnotation(declaration: KSClassDeclaration) {
    val propertyTypes = declaration
        .getDeclaredProperties()
        .mapIndexed { index, it -> it.simpleName.asString() to Property(index, it) }
        .toMap()
    declaration.getEithers().forEach {
        it.names.forEach { name ->
            val property = propertyTypes[name] ?: run {
                logger.error("Either type does not exist", declaration)
                return
            }

            if (!property.isAnnotationPresent(NullIfDefault::class)) {
                logger.error("Either can only be used with @NullIfDefault values", declaration)
                return
            }
        }
    }
    declaration.getDeclaredProperties().forEachIndexed { index, it ->
        if (it.isAnnotationPresent(OtherIfDefault::class)) {
            if (it.isAnnotationPresent(NullIfDefault::class)) {
                logger.error("Usage of both @NullIfDefault and @OtherIfDefault is not allowed", it)
            } else {
                val name = it.getOtherIfDefault().name
                val property = propertyTypes[name] ?: return run {
                    logger.error("Specified property name does not exist: $name", it)
                }
                if (!it.type.resolve().isAssignableFrom(property.type.resolve())) {
                    logger.error("Specified property has different type! Expected ${it.type} found $property", it)
                    return
                }
                if (index < property.index) {
                    logger.error("Specified property comes after current property", it)
                    return
                }
            }
        }
    }
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
