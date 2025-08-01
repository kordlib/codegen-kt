package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import org.jetbrains.annotations.NotNull

@NotNull(`value` = "")
public inline fun PropertySpec.Builder.getter(block: FunSpecBuilderScope = {}): FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return FunSpec.getter(block).also(::getter)
}

@NotNull(`value` = "")
public inline fun PropertySpec.Builder.setter(block: FunSpecBuilderScope = {}): FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return FunSpec.setter(block).also(::setter)
}
