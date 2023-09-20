@file:JvmName("AnnotatorGenerator")

package dev.kord.codegen.generator.annotator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.kord.codegen.generator.utils.ADD_ANNOTATION
import dev.kord.codegen.generator.utils.mapToValueParameterList
import dev.kord.codegen.kotlinpoet.CodeBlock
import dev.kord.codegen.kotlinpoet.addFunction
import dev.kord.codegen.kotlinpoet.withControlFlow
import dev.kord.codegen.ksp.annotations.AnnotationArguments.Companion.arguments
import dev.kord.codegen.ksp.getAnnotationByType
import dev.kord.codegen.generator.utils.FileSpec

@OptIn(KspExperimental::class)
fun SymbolProcessorEnvironment.processAnnotators(resolver: Resolver, origin: KSFile, annotators: Sequence<Annotator>) {
    val fileSpec = FileSpec(origin.packageName.asString(), origin.fileName.dropLast(3)) {
        annotators.forEach { annotator ->
            resolver.getDeclarationsFromPackage(annotator.fromPackage)
                .filterIsInstance<KSClassDeclaration>()
                .filter { it.classKind == ClassKind.ANNOTATION_CLASS }
                .filter { it.simpleName.asString() !in annotator.ignore }
                .forEach {
                    it.generateAnnotator(origin, this)
                }
        }
    }

    fileSpec.writeTo(codeGenerator, false)
}

private fun KSClassDeclaration.generateAnnotator(origin: KSFile, builder: FileSpec.Builder) {
    receiversByTarget.forEach {
        builder.addFunction(simpleName.asString().replaceFirstChar { it.lowercase() }) {
            addKdoc("Adds `%N` to this [%T]", toClassName().simpleName, it.enclosingClassName()!!)
            addOriginatingKSFile(origin)
            receiver(it)
            primaryConstructor!!.parameters.forEach {
                addParameter(it.name!!.asString(), it.type.toTypeName())
            }

            val type = toClassName()

            if (parameters.isEmpty()) {
                addCode(
                    "%M(%L)",
                    ADD_ANNOTATION,
                    type.toLiteral()
                )
            } else {
                withControlFlow(
                    "%M(%L)", ADD_ANNOTATION,
                    type.toLiteral()
                ) {
                    val placeHolders = parameters.map {
                        if (it.type == STRING) {
                            CodeBlock.of("%%S")
                        } else {
                            CodeBlock.of("%%L")
                        }
                    }.joinToCode(", ")

                    addStatement("addMember(%S, %L)", placeHolders, parameters.mapToValueParameterList())
                }
            }
        }
    }
}

private fun ClassName.toLiteral() = CodeBlock {
    add("%T(", ClassName::class.asClassName())
    val parameters = (listOf(packageName) + simpleNames).map {
        CodeBlock.of("%S", it)
    }.joinToCode(", ")
    add("%L", parameters)
    add(")")
}
