package dev.kord.codegen.generator.annotator

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.*
import dev.kord.codegen.ksp.annotations.AnnotationArguments.Companion.arguments
import dev.kord.codegen.ksp.getAnnotationByType
import dev.kord.codegen.ksp.getAnnotationsByType
import kotlin.reflect.KClass

val KSClassDeclaration.receiversByTarget: List<ClassName>
    get() {
        val targets = if (getAnnotationsByType<Target>().none()) {
            AnnotationTarget.entries
        } else {
            val targetAnnotation = getAnnotationByType<Target>()
            val targetsRaw = targetAnnotation.arguments<Target>()

            @Suppress("INVISIBLE_REFERENCE")
            val out = with(targetsRaw) { Target::allowedTargets.value }

            @Suppress("UNCHECKED_CAST")
            out as List<AnnotationTarget>
        }

        return buildList {
            if (AnnotationTarget.FILE in targets) {
                add(FileSpec.Builder::class)
            }
            if (AnnotationTarget.FUNCTION in targets || AnnotationTarget.PROPERTY_GETTER in targets || AnnotationTarget.PROPERTY_SETTER in targets) {
                add(FunSpec.Builder::class)
            }
            if (AnnotationTarget.VALUE_PARAMETER in targets) {
                add(ParameterSpec.Builder::class)
            }
            if (AnnotationTarget.CLASS in targets) {
                add(TypeSpec.Builder::class)
            }
            if (AnnotationTarget.TYPEALIAS in targets) {
                add(TypeAliasSpec.Builder::class)
            }
            if (AnnotationTarget.PROPERTY in targets) {
                add(PropertySpec.Builder::class)
            }
        }.map(KClass<*>::asClassName)
    }
