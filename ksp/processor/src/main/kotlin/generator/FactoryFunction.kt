package dev.kord.codegen.ksp.processor.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import dev.kord.codegen.kotlinpoet.addCode
import dev.kord.codegen.kotlinpoet.addFunction
import dev.kord.codegen.kotlinpoet.addParameter
import dev.kord.codegen.kotlinpoet.withControlFlow
import dev.kord.codegen.ksp.annotations.Either
import dev.kord.codegen.ksp.annotations.NullIfDefault
import dev.kord.codegen.ksp.annotations.OtherIfDefault
import dev.kord.codegen.ksp.processor.ARGUMENTS
import dev.kord.codegen.ksp.processor.ARGUMENTS_NOT_NULL
import dev.kord.codegen.ksp.processor.getEithers
import dev.kord.codegen.ksp.processor.getOtherIfDefault

context(builder: TypeSpec.Builder)
@OptIn(KspExperimental::class)
fun KSClassDeclaration.factoryFunction(packageName: String) {
    builder.addFunction(simpleName.asString()) {
        val type = ClassName(packageName, toClassName().simpleNames)
        addKdoc("Creates an [%T] from an [%T].", type, KSAnnotation::class.asClassName())
        val annotation by addParameter(KSAnnotation::class.asClassName())
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

                if (it.isAnnotationPresent(NullIfDefault::class) || it.isAnnotationPresent(OtherIfDefault::class)) {
                    withControlFlow(".takeIf") {
                        add("!arguments.isDefault(%T::%L)", toClassName(), it.simpleName.asString())
                    }

                    if (it.isAnnotationPresent(OtherIfDefault::class)) {
                        add("?:·%L", it.getOtherIfDefault().name)
                    }
                }
                add("\n")
            }
            addCode(property)
            CodeBlock.of("%L", it.simpleName.asString())
        }.joinToString(", ")

        if (isAnnotationPresent(Either::class)) {
            getEithers().forEach { (names, exclusive) ->
                addCode {
                    val nameList = names.map { CodeBlock.of("%N", it) }.joinToCode(", ")
                    val condition = CodeBlock.of("it != null")
                    val check = buildCodeBlock {
                        add("listOf(%L)", nameList)
                        if (exclusive) {
                            add(".singleOrNull { %L }", condition)
                            add("·==·null")
                        } else {
                            add(".none { %L }", condition)
                        }
                    }
                    withControlFlow("if (%L)", check) {
                        add("error(%S)", "Either of these values must be present: $names")
                        add("\n")
                    }
                    add("\n")
                }
            }
        }

        addCode("return·%T(%L)", type, valueArguments)
    }
}
