package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.Documentable
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import org.jetbrains.annotations.NotNull

@NotNull(`value` = "")
public inline fun FunSpec.Builder.addCode(block: CodeBlockBuilderScope = {}): CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::addCode)
}

@NotNull(`value` = "")
public inline fun FileSpec.Builder.addCode(block: CodeBlockBuilderScope = {}): CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::addCode)
}

@NotNull(`value` = "")
public inline fun TypeSpec.Builder.addInitializerBlock(block: CodeBlockBuilderScope = {}): CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::addInitializerBlock)
}

@NotNull(`value` = "")
public inline fun PropertySpec.Builder.`delegate`(block: CodeBlockBuilderScope = {}): CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::delegate)
}

@NotNull(`value` = "")
public inline fun Documentable.Builder<*>.addKdoc(block: CodeBlockBuilderScope = {}): CodeBlock {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return CodeBlock(block).also(::addKdoc)
}
