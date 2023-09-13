package dev.kord.codegen.generator.utils

/**
 * A function which only runs once.
 */
class RunOnceFunction<A1, A2>(private val action: (A1, A2) -> Unit) {
    private var invoked: Boolean = false

    operator fun invoke(argument1: A1, argument2: A2)  {
        if (!invoked) {
            invoked = true
            action(argument1, argument2)
        }
    }
}
