package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String

public inline fun <reified T> PropertySpec.toBuilder(name: String = this.name): PropertySpec.Builder = toBuilder(name, typeNameOf<T>())

public inline fun <reified R> PropertySpec.Builder.`receiver`(): PropertySpec.Builder = `receiver`(typeNameOf<R>())

public inline fun <reified A> PropertySpec.Builder.addAnnotation(): PropertySpec.Builder = addAnnotation(A::class.asClassName())
