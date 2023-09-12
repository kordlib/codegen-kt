package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.typeNameOf
import dev.kord.codegen.kotlinpoet.`delegate`.SubSpecDelegateProvider
import kotlin.String
import kotlin.collections.Iterable

public inline fun <reified T> FunSpec.Builder.addParameter(
  name: String,
  vararg modifiers: KModifier,
  noinline block: ParameterSpecBuilderScope = {},
): ParameterSpec = addParameter(name, typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> FunSpec.Builder.addParameter(vararg modifiers: KModifier, noinline
    block: ParameterSpecBuilderScope = {}): SubSpecDelegateProvider<ParameterSpec> =
    addParameter(typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> FunSpec.Builder.addParameter(
  name: String,
  modifiers: Iterable<KModifier>,
  noinline block: ParameterSpecBuilderScope = {},
): ParameterSpec = addParameter(name, typeNameOf<T>(), modifiers, block)

public inline fun <reified T> FunSpec.Builder.addParameter(modifiers: Iterable<KModifier>, noinline
    block: ParameterSpecBuilderScope = {}): SubSpecDelegateProvider<ParameterSpec> =
    addParameter(typeNameOf<T>(), modifiers, block)
