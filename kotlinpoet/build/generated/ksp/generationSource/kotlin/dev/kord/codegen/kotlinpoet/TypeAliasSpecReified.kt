package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeAliasSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String

public inline fun <reified T> TypeAliasSpec.toBuilder(name: String = this.name): TypeAliasSpec.Builder = toBuilder(name, typeNameOf<T>())

public inline fun <reified A> TypeAliasSpec.Builder.addAnnotation(): TypeAliasSpec.Builder = addAnnotation(A::class.asClassName())
