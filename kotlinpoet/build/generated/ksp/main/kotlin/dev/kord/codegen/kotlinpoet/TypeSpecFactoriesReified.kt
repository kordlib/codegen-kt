package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified C> TypeSpec.Companion.`class`(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `class`(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Companion.expectClass(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return expectClass(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Companion.`object`(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `object`(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Companion.`interface`(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `interface`(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Companion.funInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return funInterface(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Companion.`enum`(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `enum`(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Companion.`annotation`(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `annotation`(C::class.asClassName(), block)
}
