package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.typeNameOf
import dev.kord.codegen.kotlinpoet.`delegate`.SubSpecDelegateProvider
import kotlin.String
import kotlin.collections.Iterable
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified T> FunSpec.Builder.addParameter(
  name: String,
  vararg modifiers: KModifier,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addParameter(name, typeNameOf<T>(), modifiers = modifiers, block)
}

public inline fun <reified T> FunSpec.Builder.addParameter(vararg modifiers: KModifier, noinline block: ParameterSpecBuilderScope = {}): SubSpecDelegateProvider<ParameterSpec> = addParameter(typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> FunSpec.Builder.addParameter(
  name: String,
  modifiers: Iterable<KModifier>,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addParameter(name, typeNameOf<T>(), modifiers, block)
}

public inline fun <reified T> FunSpec.Builder.addParameter(modifiers: Iterable<KModifier>, noinline block: ParameterSpecBuilderScope = {}): SubSpecDelegateProvider<ParameterSpec> = addParameter(typeNameOf<T>(), modifiers, block)
