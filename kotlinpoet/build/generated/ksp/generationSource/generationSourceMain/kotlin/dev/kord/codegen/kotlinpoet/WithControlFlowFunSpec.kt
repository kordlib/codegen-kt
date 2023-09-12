package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FunSpec

public fun FunSpec.Builder.withControlFlow(
    controlFlow: String,
    vararg args: Any,
    block: FunSpecBuilderScope,
): FunSpec.Builder = beginControlFlow(controlFlow, args = args).apply(block).endControlFlow()
