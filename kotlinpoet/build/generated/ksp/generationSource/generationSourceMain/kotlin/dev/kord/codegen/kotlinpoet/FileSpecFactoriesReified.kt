package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asClassName

public inline fun <reified C> FileSpec(block: FileSpecBuilderScope = {}): FileSpec =
    FileSpec(C::class.asClassName(), block)
