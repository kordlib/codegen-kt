package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String
import kotlin.collections.Iterable
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified T> ParameterSpec(
  name: String,
  vararg modifiers: KModifier,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return ParameterSpec(name, typeNameOf<T>(), modifiers = modifiers, block)
}

public inline fun <reified T> ParameterSpec(
  name: String,
  modifiers: Iterable<KModifier>,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return ParameterSpec(name, typeNameOf<T>(), modifiers, block)
}
