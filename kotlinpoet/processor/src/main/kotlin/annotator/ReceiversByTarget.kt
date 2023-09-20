package dev.kord.codegen.generator.annotator

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.*
import dev.kord.codegen.ksp.annotations.AnnotationArguments
import dev.kord.codegen.ksp.annotations.AnnotationArguments.Companion.arguments
import dev.kord.codegen.ksp.getAnnotationByType
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

val KSClassDeclaration.receiversByTarget: List<ClassName>
    get() {
        val targetAnnotation = getAnnotationByType<Target>()
        val targetsRaw = targetAnnotation.arguments<Target>()
        val targets = targetsRaw[Target::allowedTargets]!!

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
