package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

public inline fun <reified C> TypeSpec.Builder.addClass(noinline block: TypeSpecBuilderScope = {}):
    TypeSpec = addClass(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addClass(noinline block: TypeSpecBuilderScope = {}):
    TypeSpec = addClass(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addExpectClass(noinline block: TypeSpecBuilderScope =
    {}): TypeSpec = addExpectClass(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addExpectClass(noinline block: TypeSpecBuilderScope =
    {}): TypeSpec = addExpectClass(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addObject(noinline block: TypeSpecBuilderScope = {}):
    TypeSpec = addObject(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addObject(noinline block: TypeSpecBuilderScope = {}):
    TypeSpec = addObject(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addInterface(noinline block: TypeSpecBuilderScope =
    {}): TypeSpec = addInterface(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addInterface(noinline block: TypeSpecBuilderScope =
    {}): TypeSpec = addInterface(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addFunInterface(noinline block: TypeSpecBuilderScope
    = {}): TypeSpec = addFunInterface(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addFunInterface(noinline block: TypeSpecBuilderScope
    = {}): TypeSpec = addFunInterface(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addEnum(noinline block: TypeSpecBuilderScope = {}):
    TypeSpec = addEnum(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addEnum(noinline block: TypeSpecBuilderScope = {}):
    TypeSpec = addEnum(C::class.asClassName(), block)

public inline fun <reified C> TypeSpec.Builder.addAnnotationClass(noinline
    block: TypeSpecBuilderScope = {}): TypeSpec = addAnnotationClass(C::class.asClassName(), block)

public inline fun <reified C> FileSpec.Builder.addAnnotationClass(noinline
    block: TypeSpecBuilderScope = {}): TypeSpec = addAnnotationClass(C::class.asClassName(), block)
