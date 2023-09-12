package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String
import kotlin.collections.Iterable

public inline fun <reified T> ParameterSpec(
  name: String,
  vararg modifiers: KModifier,
  noinline block: ParameterSpecBuilderScope = {},
): ParameterSpec = ParameterSpec(name, typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> ParameterSpec(
  name: String,
  modifiers: Iterable<KModifier>,
  noinline block: ParameterSpecBuilderScope = {},
): ParameterSpec = ParameterSpec(name, typeNameOf<T>(), modifiers, block)
