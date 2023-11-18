@file:Suppress("INVISIBLE_REFERENCE")

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.asClassName
import kotlin.jvm.internal.CallableReference
import kotlin.jvm.internal.ClassBasedDeclarationContainer
import kotlin.reflect.KCallable
import kotlin.reflect.KClass

/**
 * Converts an [Enum constant][Enum] to a [MemberName].
 *
 * ```kotlin
 * val EXACTLY_ONCE = InvocationKind.EXACTLY_ONCE.asMemberName()
 * ```
 */
@OptIn(DelicateKotlinPoetApi::class)
public fun Enum<*>.asMemberName(): MemberName = declaringJavaClass.asClassName().member(name)


/**
 * Converts a reference to a callable (Property or function) to a [MemberName].
 *
 * ```kotlin
 * val STRING_PLUS = String::plus.asMemberName()
 * ```
 */
@DelicateKotlinPoetApi(message = "This API uses internal Kotlin reflection APIs and might break at any time")
public fun KCallable<*>.asMemberName(): MemberName {
    val reference = this as? CallableReference ?: error("asMemberName is only supported on references")
    return when (val owner = reference.owner) {
        // Technically KClass is a ClassBasedDeclarationContainer, but in this case we need more than just the package,
        // so we let kotlinpoet figure out the class hierarchy
        is KClass<*> -> owner.asClassName().member(name)
        // This represents a "file class" or a top-level kt file, so we just care about the package name
        is ClassBasedDeclarationContainer -> MemberName(owner.jClass.`package`.name, name)
        else -> error("asMemberName does not work for locals")
    }
}
