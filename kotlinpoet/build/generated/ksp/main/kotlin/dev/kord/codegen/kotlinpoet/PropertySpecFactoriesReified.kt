package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String
import kotlin.collections.Iterable
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified T> PropertySpec(
  name: String,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return PropertySpec(name, typeNameOf<T>(), modifiers = modifiers, block)
}

public inline fun <reified T> PropertySpec(
  name: String,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return PropertySpec(name, typeNameOf<T>(), modifiers, block)
}
