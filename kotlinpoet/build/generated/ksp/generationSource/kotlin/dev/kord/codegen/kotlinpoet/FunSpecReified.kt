package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf

public inline fun <reified R> FunSpec.Builder.`receiver`(kdoc: CodeBlock = emptyCodeBlock()):
    FunSpec.Builder = `receiver`(typeNameOf<R>(), kdoc)

public inline fun <reified R> FunSpec.Builder.returns(kdoc: CodeBlock = emptyCodeBlock()):
    FunSpec.Builder = returns(typeNameOf<R>(), kdoc)

public inline fun <reified A> FunSpec.Builder.addAnnotation(): FunSpec.Builder =
    addAnnotation(A::class.asClassName())
