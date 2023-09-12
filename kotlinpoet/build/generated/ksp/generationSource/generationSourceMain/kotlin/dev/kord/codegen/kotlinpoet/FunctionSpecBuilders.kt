@file:OptIn(ExperimentalContracts::class)

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.TypeSpec
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun TypeSpec.Builder.addFunction(name: String, block: FunSpecBuilderScope = {}):
    FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return FunSpec(name, block).also(::addFunction)
}

public inline fun TypeSpec.Builder.addFunction(memberName: MemberName, block: FunSpecBuilderScope =
    {}): FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return FunSpec(memberName, block).also(::addFunction)
}

public inline fun TypeSpec.Builder.addConstructor(block: FunSpecBuilderScope = {}): FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `constructor`(block).also(::addFunction)
}

public inline fun FileSpec.Builder.addFunction(name: String, block: FunSpecBuilderScope = {}):
    FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return FunSpec(name, block).also(::addFunction)
}

public inline fun FileSpec.Builder.addFunction(memberName: MemberName, block: FunSpecBuilderScope =
    {}): FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return FunSpec(memberName, block).also(::addFunction)
}
