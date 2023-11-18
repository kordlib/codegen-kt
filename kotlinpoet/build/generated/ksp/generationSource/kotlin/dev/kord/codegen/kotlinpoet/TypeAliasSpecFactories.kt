package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.TypeAliasSpec
import com.squareup.kotlinpoet.TypeName
import java.lang.reflect.Type
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.reflect.KClass

public typealias TypeAliasSpecBuilderScope = @CodeGenDsl TypeAliasSpec.Builder.() -> Unit

public inline fun TypeAliasSpec(
    name: String,
    type: TypeName,
    block: TypeAliasSpecBuilderScope = {},
): TypeAliasSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeAliasSpec.builder(name, type).apply(block).build()
}

@DelicateKotlinPoetApi(message =
        "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public inline fun TypeAliasSpec(
    name: String,
    type: Type,
    block: TypeAliasSpecBuilderScope = {},
): TypeAliasSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeAliasSpec.builder(name, type).apply(block).build()
}

public inline fun TypeAliasSpec(
    name: String,
    type: KClass<*>,
    block: TypeAliasSpecBuilderScope = {},
): TypeAliasSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeAliasSpec.builder(name, type).apply(block).build()
}
