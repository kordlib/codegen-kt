package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String
import kotlin.collections.Iterable

public inline fun <reified R> FunSpec.Builder.`receiver`(kdoc: CodeBlock = emptyCodeBlock()):
    FunSpec.Builder = `receiver`(typeNameOf<R>(), kdoc)

public inline fun <reified R> FunSpec.Builder.returns(kdoc: CodeBlock = emptyCodeBlock()):
    FunSpec.Builder = returns(typeNameOf<R>(), kdoc)

public inline fun <reified T> FunSpec.Builder.addParameter(name: String, vararg
    modifiers: KModifier): FunSpec.Builder = addParameter(name, typeNameOf<T>(),
    modifiers = modifiers)

public inline fun <reified T> FunSpec.Builder.addParameter(name: String,
    modifiers: Iterable<KModifier>): FunSpec.Builder = addParameter(name, typeNameOf<T>(),
    modifiers)

public inline fun <reified A> FunSpec.Builder.addAnnotation(): FunSpec.Builder =
    addAnnotation(A::class.asClassName())
