package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String

public inline fun <reified T> ParameterSpec.toBuilder(name: String = this.name):
    ParameterSpec.Builder = toBuilder(name, typeNameOf<T>())

public inline fun <reified A> ParameterSpec.Builder.addAnnotation(): ParameterSpec.Builder =
    addAnnotation(A::class.asClassName())

public inline fun <reified T> ParameterSpec.Companion.unnamed(): ParameterSpec =
    unnamed(typeNameOf<T>())
