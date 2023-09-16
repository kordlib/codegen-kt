package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

public inline fun <reified C> `class`(block: TypeSpecBuilderScope = {}): TypeSpec =
    `class`(C::class.asClassName(), block)

public inline fun <reified C> expectClass(block: TypeSpecBuilderScope = {}): TypeSpec =
    expectClass(C::class.asClassName(), block)

public inline fun <reified C> `object`(block: TypeSpecBuilderScope = {}): TypeSpec =
    `object`(C::class.asClassName(), block)

public inline fun <reified C> `interface`(block: TypeSpecBuilderScope = {}): TypeSpec =
    `interface`(C::class.asClassName(), block)

public inline fun <reified C> funInterface(block: TypeSpecBuilderScope = {}): TypeSpec =
    funInterface(C::class.asClassName(), block)

public inline fun <reified C> `enum`(block: TypeSpecBuilderScope = {}): TypeSpec =
    `enum`(C::class.asClassName(), block)

public inline fun <reified C> `annotation`(block: TypeSpecBuilderScope = {}): TypeSpec =
    `annotation`(C::class.asClassName(), block)
