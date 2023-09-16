package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

public inline fun <reified C> TypeSpec.Builder.addClass(block: TypeSpecBuilderScope = {}): TypeSpec
    = addClass(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addClass(block: TypeSpecBuilderScope = {}): TypeSpec
    = addClass(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addExpectClass(block: TypeSpecBuilderScope = {}):
    TypeSpec = addExpectClass(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addExpectClass(block: TypeSpecBuilderScope = {}):
    TypeSpec = addExpectClass(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addObject(block: TypeSpecBuilderScope = {}): TypeSpec
    = addObject(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addObject(block: TypeSpecBuilderScope = {}): TypeSpec
    = addObject(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec = addInterface(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec = addInterface(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addFunInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec = addFunInterface(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addFunInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec = addFunInterface(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addEnum(block: TypeSpecBuilderScope = {}): TypeSpec =
    addEnum(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addEnum(block: TypeSpecBuilderScope = {}): TypeSpec =
    addEnum(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addAnnotationClass(block: TypeSpecBuilderScope = {}):
    TypeSpec = addAnnotationClass(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addAnnotationClass(block: TypeSpecBuilderScope = {}):
    TypeSpec = addAnnotationClass(C::class.asClassName(), block)
