package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec

public fun FileSpec.Builder.withControlFlow(
    controlFlow: String,
    vararg args: Any,
    block: FileSpecBuilderScope,
): FileSpec.Builder = beginControlFlow(controlFlow, args = args).apply(block).endControlFlow()
