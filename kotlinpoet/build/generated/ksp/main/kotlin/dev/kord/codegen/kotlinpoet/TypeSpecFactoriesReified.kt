package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.ReplaceWith
import kotlin.Suppress
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified C> TypeSpec.Companion.`class`(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `class`(C::class.asClassName(), block)
}

@Deprecated(
  message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
  replaceWith = ReplaceWith(expression =
      "TypeSpec.classBuilder(className).addModifiers(KModifier.EXPECT)"),
  level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
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
