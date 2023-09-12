package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String
import kotlin.collections.Iterable

public inline fun <reified T> PropertySpec.toBuilder(name: String = this.name): PropertySpec.Builder
    = toBuilder(name, typeNameOf<T>())

public inline fun <reified R> PropertySpec.Builder.`receiver`(): PropertySpec.Builder =
    `receiver`(typeNameOf<R>())

public inline fun <reified A> PropertySpec.Builder.addAnnotation(): PropertySpec.Builder =
    addAnnotation(A::class.asClassName())

public inline fun <reified T> PropertySpec.Companion.builder(name: String, vararg
    modifiers: KModifier): PropertySpec.Builder = builder(name, typeNameOf<T>(),
    modifiers = modifiers)

public inline fun <reified T> PropertySpec.Companion.builder(name: String,
    modifiers: Iterable<KModifier>): PropertySpec.Builder = builder(name, typeNameOf<T>(),
    modifiers)
