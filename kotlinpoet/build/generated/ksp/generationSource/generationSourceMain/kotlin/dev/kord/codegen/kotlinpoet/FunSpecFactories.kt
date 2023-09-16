@file:OptIn(ExperimentalContracts::class)

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public typealias FunSpecBuilderScope = @CodeGenDsl FunSpec.Builder.() -> Unit

public inline fun FunSpec(name: String, block: FunSpecBuilderScope = {}): FunSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return FunSpec.builder(name).apply(block).build()
}

public inline fun FunSpec(memberName: MemberName, block: FunSpecBuilderScope = {}): FunSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return FunSpec.builder(memberName).apply(block).build()
}

public inline fun FunSpec.Companion.`constructor`(block: FunSpecBuilderScope = {}): FunSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return FunSpec.constructorBuilder().apply(block).build()
}

public inline fun FunSpec.Companion.getter(block: FunSpecBuilderScope = {}): FunSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return FunSpec.getterBuilder().apply(block).build()
}

public inline fun FunSpec.Companion.setter(block: FunSpecBuilderScope = {}): FunSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return FunSpec.setterBuilder().apply(block).build()
}
