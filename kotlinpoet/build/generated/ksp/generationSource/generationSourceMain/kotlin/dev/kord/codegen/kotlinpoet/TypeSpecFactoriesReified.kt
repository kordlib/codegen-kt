package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

public inline fun <reified C> TypeSpec.Companion.`class`(block: TypeSpecBuilderScope = {}): TypeSpec
    = `class`(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Companion.expectClass(block: TypeSpecBuilderScope = {}):
    TypeSpec = expectClass(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Companion.`object`(block: TypeSpecBuilderScope = {}):
    TypeSpec = `object`(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Companion.`interface`(block: TypeSpecBuilderScope = {}):
    TypeSpec = `interface`(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Companion.funInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec = funInterface(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Companion.`enum`(block: TypeSpecBuilderScope = {}): TypeSpec
    = `enum`(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Companion.`annotation`(block: TypeSpecBuilderScope = {}):
    TypeSpec = `annotation`(C::class.asClassName(), block)
