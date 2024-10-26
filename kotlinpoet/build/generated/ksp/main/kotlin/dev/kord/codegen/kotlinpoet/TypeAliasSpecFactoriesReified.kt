package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeAliasSpec
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified T> TypeAliasSpec(name: String, block: TypeAliasSpecBuilderScope = {}): TypeAliasSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeAliasSpec(name, typeNameOf<T>(), block)
}
