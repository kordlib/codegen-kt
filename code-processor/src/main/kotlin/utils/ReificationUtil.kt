package dev.kord.codegen.generator.utils

import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.ksp.TypeParameterResolver

/**
 * Returns a [TypeParameterResolver] for this list of [TypeVariableNames][TypeVariableName] for use
 * with enclosed declarations.
 *
 * @param parent the optional parent resolver, if any. An example of this is cases where you might
 *               create a resolver for a [KSFunction] and supply a parent resolved from the
 *               enclosing [KSClassDeclaration].
 * @param sourceTypeHint an optional hint for error messages. Unresolvable parameter IDs will
 *                       include this hint in the thrown error's message.
 */
fun List<TypeVariableName>.toTypeParameterResolver(
    parent: TypeParameterResolver? = null,
    sourceTypeHint: String = "<unknown>",
): TypeParameterResolver {
    val parametersMap = LinkedHashMap<String, TypeVariableName>()
    val typeParamResolver = { id: String ->
        parametersMap[id]
            ?: parent?.get(id)
            ?: throw IllegalStateException(
                "No type argument found for $id! Analyzed $sourceTypeHint with known parameters " +
                    "${parametersMap.keys}",
            )
    }

    val resolver = object : TypeParameterResolver {
        override val parametersMap: Map<String, TypeVariableName> = parametersMap

        override operator fun get(index: String): TypeVariableName = typeParamResolver(index)
    }

    // Fill the parametersMap. Need to do sequentially and allow for referencing previously defined params
    for (typeVar in this) {
        // Put the simple typevar in first, then it can be referenced in the full toTypeVariable()
        // replacement later that may add bounds referencing this.
        val id = typeVar.name
        parametersMap[id] = TypeVariableName(id)
    }

    for (typeVar in this) {
        val id = typeVar.name
        // Now replace it with the full version.
        parametersMap[id] = typeVar
    }

    return resolver
}
