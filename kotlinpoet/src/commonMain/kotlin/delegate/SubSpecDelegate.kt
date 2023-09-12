package dev.kord.codegen.kotlinpoet.delegate

import dev.kord.codegen.kotlinpoet.CodeGenInternal
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * This class is not supposed to be used directly.
 *
 * it is used by the processor to allow for syntax like this
 *
 * ```kotlin
 * val name by addProperty<String> {
 *     initializer("%S", "Kodee")
 * }
 * ```
 *
 * This produces
 * ```
 * val name = "Kodee"
 * ```
 */
@CodeGenInternal
public class SubSpecDelegateProvider<T>(private val creator: (String) -> T) {
    public operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadOnlyProperty<Any?, T> =
        SubSpecDelegate(creator(property.name))
}

@PublishedApi
internal fun <T> produceByName(creator: (String) -> T): SubSpecDelegateProvider<T> = SubSpecDelegateProvider(creator)

private class SubSpecDelegate<T>(private val spec: T) : ReadOnlyProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = spec
}
