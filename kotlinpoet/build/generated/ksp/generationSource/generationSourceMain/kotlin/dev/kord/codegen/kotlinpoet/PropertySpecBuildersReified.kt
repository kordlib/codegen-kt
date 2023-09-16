package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.typeNameOf
import dev.kord.codegen.kotlinpoet.`delegate`.SubSpecDelegateProvider
import kotlin.String
import kotlin.collections.Iterable

public inline fun <reified T> TypeSpec.Builder.addProperty(
  name: String,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): PropertySpec = addProperty(name, typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> TypeSpec.Builder.addProperty(vararg modifiers: KModifier, noinline
    block: PropertySpecBuilderScope = {}): SubSpecDelegateProvider<PropertySpec> =
    addProperty(typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> TypeSpec.Builder.addProperty(
  name: String,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): PropertySpec = addProperty(name, typeNameOf<T>(), modifiers, block)

public inline fun <reified T> TypeSpec.Builder.addProperty(modifiers: Iterable<KModifier>, noinline
    block: PropertySpecBuilderScope = {}): SubSpecDelegateProvider<PropertySpec> =
    addProperty(typeNameOf<T>(), modifiers, block)

public inline fun <reified T> FileSpec.Builder.addProperty(
  name: String,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): PropertySpec = addProperty(name, typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> FileSpec.Builder.addProperty(vararg modifiers: KModifier, noinline
    block: PropertySpecBuilderScope = {}): SubSpecDelegateProvider<PropertySpec> =
    addProperty(typeNameOf<T>(), modifiers = modifiers, block)

public inline fun <reified T> FileSpec.Builder.addProperty(
  name: String,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): PropertySpec = addProperty(name, typeNameOf<T>(), modifiers, block)

public inline fun <reified T> FileSpec.Builder.addProperty(modifiers: Iterable<KModifier>, noinline
    block: PropertySpecBuilderScope = {}): SubSpecDelegateProvider<PropertySpec> =
    addProperty(typeNameOf<T>(), modifiers, block)
