package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import java.lang.reflect.Type
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.reflect.KClass

public typealias PropertySpecBuilderScope = @CodeGenDsl PropertySpec.Builder.() -> Unit

public inline fun PropertySpec(
    name: String,
    type: TypeName,
    vararg modifiers: KModifier,
    block: PropertySpecBuilderScope = {},
): PropertySpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return PropertySpec.builder(name, type, modifiers = modifiers).apply(block).build()
}

public inline fun PropertySpec(
    name: String,
    type: Type,
    vararg modifiers: KModifier,
    block: PropertySpecBuilderScope = {},
): PropertySpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return PropertySpec.builder(name, type, modifiers = modifiers).apply(block).build()
}

public inline fun PropertySpec(
    name: String,
    type: KClass<*>,
    vararg modifiers: KModifier,
    block: PropertySpecBuilderScope = {},
): PropertySpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return PropertySpec.builder(name, type, modifiers = modifiers).apply(block).build()
}

public inline fun PropertySpec(
    name: String,
    type: TypeName,
    modifiers: Iterable<KModifier>,
    block: PropertySpecBuilderScope = {},
): PropertySpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return PropertySpec.builder(name, type, modifiers).apply(block).build()
}

@DelicateKotlinPoetApi(message = "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public inline fun PropertySpec(
    name: String,
    type: Type,
    modifiers: Iterable<KModifier>,
    block: PropertySpecBuilderScope = {},
): PropertySpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return PropertySpec.builder(name, type, modifiers).apply(block).build()
}

public inline fun PropertySpec(
    name: String,
    type: KClass<*>,
    modifiers: Iterable<KModifier>,
    block: PropertySpecBuilderScope = {},
): PropertySpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return PropertySpec.builder(name, type, modifiers).apply(block).build()
}
