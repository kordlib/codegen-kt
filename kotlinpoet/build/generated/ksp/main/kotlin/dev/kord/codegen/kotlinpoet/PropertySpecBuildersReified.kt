package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberSpecHolder
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.typeNameOf
import dev.kord.codegen.kotlinpoet.`delegate`.SubSpecDelegateProvider
import kotlin.String
import kotlin.collections.Iterable
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified T> MemberSpecHolder.Builder<*>.addProperty(
  name: String,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addProperty(name, typeNameOf<T>(), modifiers = modifiers, block)
}

public inline fun <reified T> MemberSpecHolder.Builder<*>.addProperty(vararg modifiers: KModifier,
    noinline block: PropertySpecBuilderScope = {}): SubSpecDelegateProvider<PropertySpec> =
    addProperty(typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> MemberSpecHolder.Builder<*>.addProperty(
  name: String,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addProperty(name, typeNameOf<T>(), modifiers, block)
}

public inline fun <reified T>
    MemberSpecHolder.Builder<*>.addProperty(modifiers: Iterable<KModifier>, noinline
    block: PropertySpecBuilderScope = {}): SubSpecDelegateProvider<PropertySpec> =
    addProperty(typeNameOf<T>(), modifiers, block)
