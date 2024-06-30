package dev.kord.codegen.ksp.annotations

import com.google.devtools.ksp.isDefault
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSValueArgument
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

/**
 * Container of the arguments of an [KSAnnotation].
 *
 * @see get
 */
public class AnnotationArguments<A : Annotation> private constructor(
    private val arguments: Map<String, KSValueArgument>
) {
    private fun getArgument(parameter: KProperty1<A, Any>) = arguments.getValue(parameter.name)

    @PublishedApi
    internal val KProperty1<A, Any>.value: Any? get() = getArgument(this).value

    /**
     * Checks whether the [parameter] uses its default value.
     */
    public fun isDefault(parameter: KProperty1<A, Any>): Boolean = getArgument(parameter).isDefault()

    // can't return non-nullable values because of

    /**
     * Returns the value of [parameter] as an [KSAnnotation] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    public operator fun get(parameter: KProperty1<A, Annotation>): KSAnnotation? = parameter.value as KSAnnotation?

    /**
     * Returns the value of [parameter] as a [List] of [KSAnnotations][KSAnnotation] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getAnnotationArray")
    public operator fun get(parameter: KProperty1<A, Array<out Annotation>>): List<KSAnnotation>? =
        @Suppress("UNCHECKED_CAST") (parameter.value as List<KSAnnotation>?)

    /**
     * Returns the value of [parameter] as an [Number] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getNumber")
    public inline operator fun <reified T : Number> get(parameter: KProperty1<A, T>): T? =
        parameter.value as T?

    /**
     * Returns the value of [parameter] as an [String] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getString")
    public operator fun get(parameter: KProperty1<A, String>): String? =
        parameter.value as String?

    /**
     * Returns the value of [parameter] as an [Boolean] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getBoolean")
    public operator fun get(parameter: KProperty1<A, Boolean>): Boolean? =
        parameter.value as Boolean?

    /**
     * Returns the value of [parameter] as an [KSType] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getKClass")
    public operator fun get(parameter: KProperty1<A, KClass<*>>): KSType? =
        parameter.value as KSType?

    @JvmName("getEnum")
    public inline operator fun <reified T : Enum<T>> get(parameter: KProperty1<A, T>): T? =
        (parameter.value as KSType?)?.let {
            enumValueOf<T>(it.declaration.simpleName.asString())
        }

    /**
     * Returns the value of [parameter] as a [List] of [Numbers][Number] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getNumberArray")
    public inline operator fun <reified T : Number> get(parameter: KProperty1<A, Array<out T>>): List<T>? =
        @Suppress("UNCHECKED_CAST") (parameter.value as List<T>?)

    /**
     * Returns the value of [parameter] as a [List] of [Strings][String] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getStringArray")
    public operator fun get(parameter: KProperty1<A, Array<out String>>): List<String>? =
        @Suppress("UNCHECKED_CAST") (parameter.value as List<String>?)

    /**
     * Returns the value of [parameter] as a [List] of [KSTypes][KSType] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getKClassArray")
    public operator fun get(parameter: KProperty1<A, Array<out KClass<*>>>): List<KSType>? =
        @Suppress("UNCHECKED_CAST") (parameter.value as List<KSType>?)

    /**
     * Returns the value of [parameter] as a [List] of [Strings][String] or `null` if it is the default value
     * (see [NonNullAnnotationArguments] for more information).
     */
    @JvmName("getBooleanArray")
    public operator fun get(parameter: KProperty1<A, Array<out Boolean>>): List<Boolean>? =
        @Suppress("UNCHECKED_CAST") (parameter.value as List<Boolean>?)


    @JvmName("getEnumArray")
    public inline operator fun <reified T : Enum<T>> get(parameter: KProperty1<A, Array<out T>>): List<T>? =
        @Suppress("UNCHECKED_CAST") (parameter.value as List<KSType>?)?.map {
            enumValueOf<T>(it.declaration.simpleName.asString())
        }

    /**
     * Not null accessors for [AnnotationArguments].
     *
     * Theoretically an annotation argument cannot be null, however, because
     * of [google/ksp#885](https://github.com/google/ksp/issues/885) this is not true in a multiplatform context.
     *
     * **Only use this accessor if you are not targeting KMP**
     */
    public class NonNullAnnotationArguments<A : Annotation> private constructor(@PublishedApi internal val delegate: AnnotationArguments<A>) {

        /**
         * Checks whether the [parameter] uses its default value.
         */
        public fun isDefault(parameter: KProperty1<A, Any>): Boolean = delegate.isDefault(parameter)

        /**
         * Returns the value of [parameter] as an [KSAnnotation].
         */
        public operator fun get(parameter: KProperty1<A, Annotation>): KSAnnotation = delegate[parameter]!!

        /**
         * Returns the value of [parameter] as a [List] of [KSAnnotations][KSAnnotation].
         */
        @JvmName("getAnnotationArray")
        public operator fun get(parameter: KProperty1<A, Array<out Annotation>>): List<KSAnnotation> =
            delegate[parameter]!!

        /**
         * Returns the value of [parameter] as an [Number].
         */
        @JvmName("getNumber")
        public inline operator fun <reified T : Number> get(parameter: KProperty1<A, T>): T = delegate[parameter]!!

        /**
         * Returns the value of [parameter] as an [String].
         */
        @JvmName("getString")
        public operator fun get(parameter: KProperty1<A, String>): String = delegate[parameter]!!

        /**
         * Returns the value of [parameter] as an [Boolean].
         */
        @JvmName("getBoolean")
        public operator fun get(parameter: KProperty1<A, Boolean>): Boolean = delegate[parameter]!!

        /**
         * Returns the value of [parameter] as an [KClass].
         */
        @JvmName("getKClass")
        public operator fun get(parameter: KProperty1<A, KClass<*>>): KSType =
            delegate[parameter]!!

        @JvmName("getEnum")
        public inline operator fun <reified T : Enum<T>> get(parameter: KProperty1<A, T>): T = delegate[parameter]!!

        /**
         * Returns the value of [parameter] as a [List] [Numbers][Number].
         */
        @JvmName("getNumberArray")
        public inline operator fun <reified T : Number> get(parameter: KProperty1<A, Array<out T>>): List<T> =
            delegate[parameter]!!

        /**
         * Returns the value of [parameter] as a [List] [Strings][String].
         */
        @JvmName("getStringArray")
        public operator fun get(parameter: KProperty1<A, Array<out String>>): List<String> = delegate[parameter]!!

        /**
         * Returns the value of [parameter] as a [List] [KClasses][KClass].
         */
        @JvmName("getKClassArray")
        public operator fun get(parameter: KProperty1<A, Array<out KClass<*>>>): List<KSType> =
            delegate[parameter]!!

        /**
         * Returns the value of [parameter] as a [List] [Booleans][Boolean].
         */
        @JvmName("getBooleanArray")
        public operator fun get(parameter: KProperty1<A, Array<out Boolean>>): List<Boolean> =
            delegate[parameter]!!

        @JvmName("getEnumArray")
        public inline operator fun <reified T : Enum<T>> get(parameter: KProperty1<A, Array<out T>>): List<T>? =
            delegate[parameter]!!

        public companion object {
            /**
             * Not null accessors for this [AnnotationArguments]. See documentation of [NonNullAnnotationArguments]
             * for more information.
             */
            public fun <A : Annotation> AnnotationArguments<A>.notNull(): NonNullAnnotationArguments<A> =
                NonNullAnnotationArguments(this)
        }
    }

    /**
     * Constructor functions for [AnnotationArguments]
     */
    public companion object {
        /**
         * Parses the arguments for this [KSAnnotation].
         *
         * @see AnnotationArguments
         */
        public fun <A : Annotation> KSAnnotation.arguments(): AnnotationArguments<A> =
            AnnotationArguments(defaultArguments.associateBy { it.name!!.asString() } + arguments.associateBy { it.name!!.asString() })
    }
}
