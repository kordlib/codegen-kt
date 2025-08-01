package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.*

/**
 * Scope for [withNameAllocator].
 */
public typealias NamingScope = @CodeGenDsl NameAllocator.() -> Unit

/**
 * Creates an empty [CodeBlock].
 *
 * @see CodeBlock
 */
@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
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
