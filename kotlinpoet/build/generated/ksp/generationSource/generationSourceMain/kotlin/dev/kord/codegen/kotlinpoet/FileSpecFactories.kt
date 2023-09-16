package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FileSpec.Companion.`get`
import com.squareup.kotlinpoet.TypeSpec
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public typealias FileSpecBuilderScope = @CodeGenDsl FileSpec.Builder.() -> Unit

public fun FileSpec(packageName: String, typeSpec: TypeSpec): FileSpec = `get`(packageName,
        typeSpec)

public inline fun FileSpec(className: ClassName, block: FileSpecBuilderScope = {}): FileSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return FileSpec.builder(className).apply(block).build()
}

public inline fun FileSpec(
    packageName: String,
    fileName: String,
    block: FileSpecBuilderScope = {},
): FileSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return FileSpec.builder(packageName, fileName).apply(block).build()
}

public inline fun FileSpec.Companion.script(
    fileName: String,
    packageName: String = "",
    block: FileSpecBuilderScope = {},
): FileSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return FileSpec.scriptBuilder(fileName, packageName).apply(block).build()
}
