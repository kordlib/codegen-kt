package dev.kord.codegen.kotlinpoet

/**
 * DSL marker for code generation DSL components.
 *
 * @see DslMarker
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@DslMarker
@CodeGenInternal
public annotation class CodeGenDsl

/**
 * Marker annotation for internal API components.
 *
 * **DO NOT USE THESE COMPONENTS UNLESS YOU EXACTLY KNOW WHAT YOU ARE DOING**
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@CodeGenInternal
@RequiresOptIn(
    "This API is intended for use by codegen maintainers only",
    level = RequiresOptIn.Level.ERROR
)
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
public annotation class CodeGenInternal
