package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterSpec.Companion.`get`
import com.squareup.kotlinpoet.TypeName
import java.lang.reflect.Type
import javax.lang.model.element.VariableElement
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.reflect.KClass

public typealias ParameterSpecBuilderScope = @CodeGenDsl ParameterSpec.Builder.() -> Unit

@DelicateKotlinPoetApi(message =
        "Element APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun ParameterSpec(element: VariableElement): ParameterSpec = `get`(element)

public inline fun ParameterSpec(
    name: String,
    type: TypeName,
    vararg modifiers: KModifier,
    block: ParameterSpecBuilderScope = {},
): ParameterSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return ParameterSpec.builder(name, type, modifiers = modifiers).apply(block).build()
}

public inline fun ParameterSpec(
    name: String,
    type: Type,
    vararg modifiers: KModifier,
    block: ParameterSpecBuilderScope = {},
): ParameterSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return ParameterSpec.builder(name, type, modifiers = modifiers).apply(block).build()
}

public inline fun ParameterSpec(
    name: String,
    type: KClass<*>,
    vararg modifiers: KModifier,
    block: ParameterSpecBuilderScope = {},
): ParameterSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return ParameterSpec.builder(name, type, modifiers = modifiers).apply(block).build()
}

public inline fun ParameterSpec(
    name: String,
    type: TypeName,
    modifiers: Iterable<KModifier>,
    block: ParameterSpecBuilderScope = {},
): ParameterSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return ParameterSpec.builder(name, type, modifiers).apply(block).build()
}

public inline fun ParameterSpec(
    name: String,
    type: Type,
    modifiers: Iterable<KModifier>,
    block: ParameterSpecBuilderScope = {},
): ParameterSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return ParameterSpec.builder(name, type, modifiers).apply(block).build()
}

public inline fun ParameterSpec(
    name: String,
    type: KClass<*>,
    modifiers: Iterable<KModifier>,
    block: ParameterSpecBuilderScope = {},
): ParameterSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return ParameterSpec.builder(name, type, modifiers).apply(block).build()
}
