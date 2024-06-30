package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeSpecHolder
import com.squareup.kotlinpoet.asClassName
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.ReplaceWith
import kotlin.Suppress
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified C>
    TypeSpecHolder.Builder<*>.addAnnotationClass(block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addAnnotationClass(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpecHolder.Builder<*>.addClass(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addClass(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpecHolder.Builder<*>.addEnum(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addEnum(C::class.asClassName(), block)
}

@Deprecated(
  replaceWith = ReplaceWith(expression =
      "TypeSpec.classBuilder(className).addModifiers(KModifier.EXPECT)"),
  message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
  level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
public inline fun <reified C> TypeSpecHolder.Builder<*>.addExpectClass(block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addExpectClass(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpecHolder.Builder<*>.addFunInterface(block: TypeSpecBuilderScope
    = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addFunInterface(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpecHolder.Builder<*>.addInterface(block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addInterface(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpecHolder.Builder<*>.addObject(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addObject(C::class.asClassName(), block)
}
