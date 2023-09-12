package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

public inline fun <reified C> `class`(noinline block: TypeSpecBuilderScope = {}): TypeSpec =
    `class`(C::class.asClassName(), block)

public inline fun <reified C> expectClass(noinline block: TypeSpecBuilderScope = {}): TypeSpec =
    expectClass(C::class.asClassName(), block)

public inline fun <reified C> `object`(noinline block: TypeSpecBuilderScope = {}): TypeSpec =
    `object`(C::class.asClassName(), block)

public inline fun <reified C> `interface`(noinline block: TypeSpecBuilderScope = {}): TypeSpec =
    `interface`(C::class.asClassName(), block)

public inline fun <reified C> funInterface(noinline block: TypeSpecBuilderScope = {}): TypeSpec =
    funInterface(C::class.asClassName(), block)

public inline fun <reified C> `enum`(noinline block: TypeSpecBuilderScope = {}): TypeSpec =
    `enum`(C::class.asClassName(), block)

public inline fun <reified C> `annotation`(noinline block: TypeSpecBuilderScope = {}): TypeSpec =
    `annotation`(C::class.asClassName(), block)
