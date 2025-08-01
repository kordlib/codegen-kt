package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberSpecHolder
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import dev.kord.codegen.kotlinpoet.`delegate`.SubSpecDelegateProvider
import dev.kord.codegen.kotlinpoet.`delegate`.produceByName
import java.lang.reflect.Type
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.reflect.KClass
import org.jetbrains.annotations.NotNull

@NotNull(`value` = "")
public inline fun MemberSpecHolder.Builder<*>.addProperty(
  name: String,
  type: TypeName,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return PropertySpec(name, type, modifiers = modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public fun MemberSpecHolder.Builder<*>.addProperty(
  type: TypeName,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): SubSpecDelegateProvider<PropertySpec> = produceByName { name ->
  PropertySpec(name, type, modifiers = modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public inline fun MemberSpecHolder.Builder<*>.addProperty(
  name: String,
  type: Type,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return PropertySpec(name, type, modifiers = modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public fun MemberSpecHolder.Builder<*>.addProperty(
  type: Type,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): SubSpecDelegateProvider<PropertySpec> = produceByName { name ->
  PropertySpec(name, type, modifiers = modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public inline fun MemberSpecHolder.Builder<*>.addProperty(
  name: String,
  type: KClass<*>,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return PropertySpec(name, type, modifiers = modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public fun MemberSpecHolder.Builder<*>.addProperty(
  type: KClass<*>,
  vararg modifiers: KModifier,
  block: PropertySpecBuilderScope = {},
): SubSpecDelegateProvider<PropertySpec> = produceByName { name ->
  PropertySpec(name, type, modifiers = modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public inline fun MemberSpecHolder.Builder<*>.addProperty(
  name: String,
  type: TypeName,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return PropertySpec(name, type, modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public fun MemberSpecHolder.Builder<*>.addProperty(
  type: TypeName,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): SubSpecDelegateProvider<PropertySpec> = produceByName { name ->
  PropertySpec(name, type, modifiers, block).also(::addProperty)
}

@DelicateKotlinPoetApi(message = "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
@NotNull(`value` = "")
public inline fun MemberSpecHolder.Builder<*>.addProperty(
  name: String,
  type: Type,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return PropertySpec(name, type, modifiers, block).also(::addProperty)
}

@DelicateKotlinPoetApi(message = "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
@NotNull(`value` = "")
public fun MemberSpecHolder.Builder<*>.addProperty(
  type: Type,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): SubSpecDelegateProvider<PropertySpec> = produceByName { name ->
  PropertySpec(name, type, modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public inline fun MemberSpecHolder.Builder<*>.addProperty(
  name: String,
  type: KClass<*>,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): PropertySpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return PropertySpec(name, type, modifiers, block).also(::addProperty)
}

@NotNull(`value` = "")
public fun MemberSpecHolder.Builder<*>.addProperty(
  type: KClass<*>,
  modifiers: Iterable<KModifier>,
  block: PropertySpecBuilderScope = {},
): SubSpecDelegateProvider<PropertySpec> = produceByName { name ->
  PropertySpec(name, type, modifiers, block).also(::addProperty)
}
