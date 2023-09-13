@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.*
import kotlin.internal.HidesMembers

/**
 * Scope for [withNameAllocator].
 */
public typealias NamingScope = @CodeGenDsl NameAllocator.() -> Unit

/**
 * Creates an empty [CodeBlock].
 *
 * @see CodeBlock
 */
@Suppress("INVISIBLE_MEMBER")
public fun emptyCodeBlock(): CodeBlock = CodeBlock.EMPTY

/**
 * Indents this file with [width] spaces.
 *
 * @see FileSpec.Builder.indent
 */
public fun FileSpec.Builder.indentWithSpaces(width: Int = 4): FileSpec.Builder = indent(" ".repeat(width))

/**
 * Convenience function to avoid name clashes.
 *
 * ```kotlin
 * withNameAllocator {
 *   val name1 = newName("T")
 *   val name2 = newName("T")
 * }
 * ```
 */
public fun withNameAllocator(block: NamingScope): Unit = NameAllocator().block()

public fun minOf(a: ULong, b: ULong): ULong {
    return if (a <= b) a else b
}

@HidesMembers
public fun FunSpec.Builder.addParameter(parameterSpec: ParameterSpec): FunSpec.Builder = apply {

}

//private fun generateListOf() = FunSpec("minOf") {
//    addAnn(Suppress("unused"))
//    val a by addParameter<Int>()
//    val b by addParameter<Int>()
//
//    returns<Boolean>()
//    addCode("return·if·(%N·<=·%n)·a·else·b", a, b)
//}
