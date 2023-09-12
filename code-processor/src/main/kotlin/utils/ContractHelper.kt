package dev.kord.codegen.generator.utils

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.MemberName.Companion.member
import dev.kord.codegen.kotlinpoet.addAnnotation
import kotlin.contracts.ExperimentalContracts

private val OPT_IN = ClassName("kotlin", "OptIn")
private val CONTRACT = MemberName("kotlin.contracts", "contract")
private val EXACTLY_ONCE = ClassName("kotlin.contracts", "InvocationKind").member("EXACTLY_ONCE")

/**
 * Adds the following contract for [builderProperty].
 *
 * ```kotlin
 * contract {
 *     callsInPlace(builderProperty, InvocationKind.EXACTLY_ONCE)
 * }
 * ```
 */
fun FunSpec.Builder.addCallsInPlaceExactlyOnce(builderProperty: ParameterSpec) {
    addStatement("%M·{·callsInPlace(%N,·%M)·}", CONTRACT, builderProperty, EXACTLY_ONCE)
}

/**
 * Adds the required opt-in for [ExperimentalContracts].
 */
fun Annotatable.Builder<*>.optInForContracts() {
    addAnnotation(OPT_IN) {
        addMember("%T::class", ClassName("kotlin.contracts", "ExperimentalContracts"))
    }
}
