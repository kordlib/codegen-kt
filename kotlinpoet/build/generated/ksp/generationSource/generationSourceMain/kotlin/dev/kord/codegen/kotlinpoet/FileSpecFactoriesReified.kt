package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified C> FileSpec(block: FileSpecBuilderScope = {}): FileSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return FileSpec(C::class.asClassName(), block)
}
