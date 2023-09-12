package dev.kord.codegen.generator.utils

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.symbol.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.TypeParameterResolver
import com.squareup.kotlinpoet.ksp.toKModifier
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.toTypeParameterResolver

fun KSValueParameter.toParameterSpec(typeParamResolver: TypeParameterResolver = TypeParameterResolver.EMPTY, useParent: Boolean = false, noInline: Boolean = false) =
    ParameterSpec.builder(name!!.asString(), type.toTypeName(typeParamResolver), modifiers)
        .apply {
            if (name!!.asString() == "block") {
                addModifiers(KModifier.NOINLINE)
            }
            if (hasDefault) {
                if (useParent) {
                    defaultValue(CodeBlock.of("this.%N", name!!.asString()))
                    return@apply
                }
                val type = this@toParameterSpec.type.toTypeName()
                val defaultForType = when {
                    type.isNullable -> CodeBlock.of("%L", null)
                    type == CodeBlock::class.asTypeName() -> CodeBlock.of("%M()", EMPTY_CODE_BLOCK)
                    type == STRING -> CodeBlock.of("%S", "")
                    type == BOOLEAN -> CodeBlock.of("%L", false)
                    (type as? ParameterizedTypeName)?.rawType == LIST -> CodeBlock.of("emptyList()")
                    (type as? ParameterizedTypeName)?.rawType == MUTABLE_MAP -> CodeBlock.of("mutableMapOf()")
                    isVararg -> CodeBlock.of("emptyArray()")
                    name?.asString() == "block" -> CodeBlock.of("{}")
                    else -> CodeBlock.of("")
                }

                defaultValue(defaultForType)
            }
        }
        .build()

fun KSFunctionDeclaration.toFunSpec(typeParamResolver: TypeParameterResolver = TypeParameterResolver.EMPTY) =
    FunSpec.builder(simpleName.asString())
        .apply {
            val declarationTypeResolver = parentDeclaration!!.typeParameters.toTypeParameterResolver()
            val functionResolver = typeParameters.toTypeParameterResolver(declarationTypeResolver)
            addParameters(this@toFunSpec.parameters.map { it.toParameterSpec(functionResolver) })
            addModifiers(this@toFunSpec.modifiers.mapNotNull(Modifier::toKModifier))
            returns(ANY)
            returns(returnType!!.toTypeName(parentDeclaration!!.typeParameters.toTypeParameterResolver(typeParamResolver)))
        }.build()

private val KSValueParameter.modifiers: Set<KModifier>
    get() = buildSet {
        if (this is KSModifierListOwner) {
            addAll(this)
        }
        if (isVararg) {
            add(KModifier.VARARG)
        }
        if (isNoInline) {
            add(KModifier.NOINLINE)
        }
        if (isCrossInline) {
            add(KModifier.CROSSINLINE)
        }
    }

fun <T, R> Sequence<KSNode>.accept(visitor: KSVisitor<T, R>, data: T) =
    fold(emptyList<R>()) { prev, it -> prev + it.accept(visitor, data) }

fun <T> KSNode.accept(data: T, vararg visitors: KSVisitor<T, *>) {
    visitors.forEach { accept(it, data) }
}

fun KSDeclarationContainer.getDeclaredFunctions(): Sequence<KSFunctionDeclaration> {
    return when (this) {
        is KSClassDeclaration -> getDeclaredFunctions() + declarations.filterIsInstance<KSDeclarationContainer>()
            .flatMap(KSDeclarationContainer::getDeclaredFunctions)
        else -> declarations.filterIsInstance<KSFunctionDeclaration>()
    }
}
