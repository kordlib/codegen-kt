package dev.kord.codegen.ksp.processor.generator

import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import dev.kord.codegen.kotlinpoet.addFunction
import dev.kord.codegen.kotlinpoet.addParameter
import dev.kord.codegen.kotlinpoet.withControlFlow
import dev.kord.codegen.ksp.processor.ARGUMENTS
import dev.kord.codegen.ksp.processor.ARGUMENTS_NOT_NULL
import dev.kord.codegen.ksp.processor.NULL_IF_DEFAULT

context(TypeSpec.Builder)
fun KSClassDeclaration.factoryFunction(packageName: String) {
    addFunction(simpleName.asString()) {
        val type = ClassName(packageName, toClassName().simpleNames)
        addKdoc("Creates an [%T] from an [%T].", type, KSAnnotation::class.asClassName())
        val annotation by addParameter<KSAnnotation>()
        returns(type)

        addStatement("val arguments = %N.%M<%T>().%M()", annotation, ARGUMENTS, toClassName(), ARGUMENTS_NOT_NULL)
        addCode("\n")
        val valueArguments = getDeclaredProperties().map {
            val property = buildCodeBlock {
                add(
                    "val %L = arguments[%T::%L]",
                    it.simpleName.asString(),
                    toClassName(),
                    it.simpleName.asString()
                )

                val argumentType = it.type.resolve()
                if (argumentType.declaration.qualifiedName!!.asString() == "kotlin.Array") {
                    val arrayType = argumentType.arguments.first().type!!.resolve()
                    if (arrayType.isMappedAnnotation(this@factoryFunction)) {
                        add(".map(%1L.Companion::%1L)", arrayType.declaration.simpleName.asString())
                    }
                } else if (argumentType.isMappedAnnotation(this@factoryFunction)) {
                    add(".let(%1L.Companion::%1L)", argumentType.declaration.simpleName.asString())
                }

                if (it.annotations.any { it.annotationType.resolve().declaration.qualifiedName!!.asString() == NULL_IF_DEFAULT }) {
                    withControlFlow(".takeIf") {
                        add("!arguments.isDefault(%T::%L)", toClassName(), it.simpleName.asString())
                        add("\n")
                    }
                }
                add("\n")
            }
            addCode(property)
            CodeBlock.of("%L", it.simpleName.asString())
        }.joinToString(", ")
        addCode("return·%T(%L)", type, valueArguments)
    }
}
