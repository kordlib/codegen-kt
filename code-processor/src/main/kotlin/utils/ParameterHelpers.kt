package dev.kord.codegen.generator.utils

import com.squareup.kotlinpoet.*
import dev.kord.codegen.kotlinpoet.CodeBlock

/**
 * Maps an [Iterable] of [ParameterSpecs][ParameterSpec] to a [CodeBlock], representing the value parameter list
 * of a function call.
 */
fun Iterable<ParameterSpec>.mapToValueParameterList() =
    map {
        CodeBlock {
            // If the parameter is a varag, we need to spread it, which can be done
            // using the spread operator or using named arguments
            // however using named arguments works regardless of the position of the argument
            // so we use named arguments
            if (KModifier.VARARG in it.modifiers) {
                add("%N·=·", it)
            }
            add("%N", it)
        }
    }
    .joinToCode(", ")
