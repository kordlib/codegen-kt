package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeAliasSpec
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String

public inline fun <reified T> TypeAliasSpec(name: String, noinline block: TypeAliasSpecBuilderScope
    = {}): TypeAliasSpec = TypeAliasSpec(name, typeNameOf<T>(), block)
