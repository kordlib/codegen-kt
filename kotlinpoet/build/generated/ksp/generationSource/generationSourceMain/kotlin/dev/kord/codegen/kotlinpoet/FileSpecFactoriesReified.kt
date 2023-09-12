package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asClassName

public inline fun <reified C> FileSpec(noinline block: FileSpecBuilderScope = {}): FileSpec =
    FileSpec(C::class.asClassName(), block)
