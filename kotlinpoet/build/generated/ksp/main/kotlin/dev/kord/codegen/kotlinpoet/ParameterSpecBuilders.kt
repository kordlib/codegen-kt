package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import dev.kord.codegen.kotlinpoet.`delegate`.SubSpecDelegateProvider
import dev.kord.codegen.kotlinpoet.`delegate`.produceByName
import java.lang.reflect.Type
import javax.lang.model.element.VariableElement
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.reflect.KClass

public inline fun FunSpec.Builder.addParameter(
  name: String,
  type: TypeName,
  vararg modifiers: KModifier,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return ParameterSpec(name, type, modifiers = modifiers, block).also(::addParameter)
}

public fun FunSpec.Builder.addParameter(
  type: TypeName,
  vararg modifiers: KModifier,
  block: ParameterSpecBuilderScope = {},
): SubSpecDelegateProvider<ParameterSpec> = produceByName { name ->
  ParameterSpec(name, type, modifiers = modifiers, block).also(::addParameter)
}

public inline fun FunSpec.Builder.addParameter(
  name: String,
  type: Type,
  vararg modifiers: KModifier,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return ParameterSpec(name, type, modifiers = modifiers, block).also(::addParameter)
}

public fun FunSpec.Builder.addParameter(
  type: Type,
  vararg modifiers: KModifier,
  block: ParameterSpecBuilderScope = {},
): SubSpecDelegateProvider<ParameterSpec> = produceByName { name ->
  ParameterSpec(name, type, modifiers = modifiers, block).also(::addParameter)
}

public inline fun FunSpec.Builder.addParameter(
  name: String,
  type: KClass<*>,
  vararg modifiers: KModifier,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return ParameterSpec(name, type, modifiers = modifiers, block).also(::addParameter)
}

public fun FunSpec.Builder.addParameter(
  type: KClass<*>,
  vararg modifiers: KModifier,
  block: ParameterSpecBuilderScope = {},
): SubSpecDelegateProvider<ParameterSpec> = produceByName { name ->
  ParameterSpec(name, type, modifiers = modifiers, block).also(::addParameter)
}

public inline fun FunSpec.Builder.addParameter(
  name: String,
  type: TypeName,
  modifiers: Iterable<KModifier>,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return ParameterSpec(name, type, modifiers, block).also(::addParameter)
}

public fun FunSpec.Builder.addParameter(
  type: TypeName,
  modifiers: Iterable<KModifier>,
  block: ParameterSpecBuilderScope = {},
): SubSpecDelegateProvider<ParameterSpec> = produceByName { name ->
  ParameterSpec(name, type, modifiers, block).also(::addParameter)
}

public inline fun FunSpec.Builder.addParameter(
  name: String,
  type: Type,
  modifiers: Iterable<KModifier>,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return ParameterSpec(name, type, modifiers, block).also(::addParameter)
}

public fun FunSpec.Builder.addParameter(
  type: Type,
  modifiers: Iterable<KModifier>,
  block: ParameterSpecBuilderScope = {},
): SubSpecDelegateProvider<ParameterSpec> = produceByName { name ->
  ParameterSpec(name, type, modifiers, block).also(::addParameter)
}

public inline fun FunSpec.Builder.addParameter(
  name: String,
  type: KClass<*>,
  modifiers: Iterable<KModifier>,
  block: ParameterSpecBuilderScope = {},
): ParameterSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return ParameterSpec(name, type, modifiers, block).also(::addParameter)
}

public fun FunSpec.Builder.addParameter(
  type: KClass<*>,
  modifiers: Iterable<KModifier>,
  block: ParameterSpecBuilderScope = {},
): SubSpecDelegateProvider<ParameterSpec> = produceByName { name ->
  ParameterSpec(name, type, modifiers, block).also(::addParameter)
}

@DelicateKotlinPoetApi(message = "Element APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun FunSpec.Builder.addParameter(element: VariableElement): ParameterSpec = ParameterSpec(element).also(::addParameter)
