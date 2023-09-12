package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String
import kotlin.collections.Iterable

public inline fun <reified T> ParameterSpec.toBuilder(name: String = this.name):
    ParameterSpec.Builder = toBuilder(name, typeNameOf<T>())

public inline fun <reified A> ParameterSpec.Builder.addAnnotation(): ParameterSpec.Builder =
    addAnnotation(A::class.asClassName())

public inline fun <reified T> ParameterSpec.Companion.builder(name: String, vararg
    modifiers: KModifier): ParameterSpec.Builder = builder(name, typeNameOf<T>(),
    modifiers = modifiers)

public inline fun <reified T> ParameterSpec.Companion.builder(name: String,
    modifiers: Iterable<KModifier>): ParameterSpec.Builder = builder(name, typeNameOf<T>(),
    modifiers)

public inline fun <reified T> ParameterSpec.Companion.unnamed(): ParameterSpec =
    unnamed(typeNameOf<T>())
