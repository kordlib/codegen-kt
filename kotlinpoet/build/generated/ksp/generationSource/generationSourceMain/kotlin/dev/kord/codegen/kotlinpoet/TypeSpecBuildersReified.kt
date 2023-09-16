package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified C> TypeSpec.Builder.addClass(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addClass(C::class.asClassName(), block)
}

public inline fun <reified C> FileSpec.Builder.addClass(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addClass(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Builder.addExpectClass(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addExpectClass(C::class.asClassName(), block)
}

public inline fun <reified C> FileSpec.Builder.addExpectClass(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addExpectClass(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Builder.addObject(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addObject(C::class.asClassName(), block)
}

public inline fun <reified C> FileSpec.Builder.addObject(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addObject(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Builder.addInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addInterface(C::class.asClassName(), block)
}

public inline fun <reified C> FileSpec.Builder.addInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addInterface(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Builder.addFunInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addFunInterface(C::class.asClassName(), block)
}

public inline fun <reified C> FileSpec.Builder.addFunInterface(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addFunInterface(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Builder.addEnum(block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addEnum(C::class.asClassName(), block)
}

public inline fun <reified C> FileSpec.Builder.addEnum(block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addEnum(C::class.asClassName(), block)
}

public inline fun <reified C> TypeSpec.Builder.addAnnotationClass(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addAnnotationClass(C::class.asClassName(), block)
}

public inline fun <reified C> FileSpec.Builder.addAnnotationClass(block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addAnnotationClass(C::class.asClassName(), block)
}
