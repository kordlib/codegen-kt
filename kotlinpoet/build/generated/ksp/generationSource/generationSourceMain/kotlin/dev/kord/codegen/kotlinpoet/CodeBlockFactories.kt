@file:OptIn(ExperimentalContracts::class)

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.CodeBlock
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public typealias CodeBlockBuilderScope = @CodeGenDsl CodeBlock.Builder.() -> Unit

public inline fun CodeBlock(block: CodeBlockBuilderScope = {}): CodeBlock {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return CodeBlock.builder().apply(block).build()
}
