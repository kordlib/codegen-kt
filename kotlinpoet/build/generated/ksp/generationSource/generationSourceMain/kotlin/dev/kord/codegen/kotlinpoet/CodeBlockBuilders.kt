package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun FunSpec.Builder.addCode(block: CodeBlockBuilderScope = {}): CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::addCode)
}

public inline fun FileSpec.Builder.addCode(block: CodeBlockBuilderScope = {}): CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::addCode)
}

public inline fun TypeSpec.Builder.addInitializerBlock(block: CodeBlockBuilderScope = {}):
    CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::addInitializerBlock)
}

public inline fun PropertySpec.Builder.`delegate`(block: CodeBlockBuilderScope = {}): CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::delegate)
}
