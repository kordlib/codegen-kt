package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.CodeBlock

public fun CodeBlock.Builder.withControlFlow(
    controlFlow: String,
    vararg args: Any?,
    block: CodeBlockBuilderScope,
): CodeBlock.Builder = beginControlFlow(controlFlow, args = args).apply(block).endControlFlow()
