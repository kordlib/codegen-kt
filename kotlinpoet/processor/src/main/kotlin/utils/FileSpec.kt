package dev.kord.codegen.generator.utils

import com.squareup.kotlinpoet.FileSpec

/**
 * Creates a [FileSpec] with [packageName] and [fileName] and applied shared settings to it.
 */
fun FileSpec(packageName: String, fileName: String, builder: FileSpec.Builder.() -> Unit) =
    FileSpec.builder(packageName, fileName).apply {
        addKotlinDefaultImports(includeJvm = true, includeJs = false)
        indent(" ".repeat(4))
        builder()
    }.build()
