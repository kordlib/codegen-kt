package dev.kord.codegen.ksp.processor.generator

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.ksp.toClassName
import dev.kord.codegen.kotlinpoet.addFunction
import dev.kord.codegen.ksp.processor.GET_ANNOTATIONS_BY_TYPE
import dev.kord.codegen.ksp.processor.GET_ANNOTATION_BY_TYPE

fun ProcessingContext.accessorFunction() {
    val dataClass = ClassName(packageName, declaration.simpleName.asString())
    declaration.allAccessorFunctions(dataClass, file)
}

private fun KSClassDeclaration.allAccessorFunctions(dataClass: ClassName, file: FileSpec.Builder) {
    accessorFunction(dataClass, file)
    declarations
        .filterIsInstance<KSClassDeclaration>()
        .filter { it.classKind == ClassKind.ANNOTATION_CLASS }
        .forEach {
            it.allAccessorFunctions(dataClass, file)
        }
}

private fun KSClassDeclaration.accessorFunction(dataClass: ClassName, file: FileSpec.Builder) {
    val targets = annotations.firstOrNull {
        it.annotationType.resolve().declaration.qualifiedName!!.asString() == Target::class.qualifiedName
    }
    if (targets != null && (targets.arguments.first().value as List<*>).isEmpty()) return
    val repeatable = annotations.any {
        it.annotationType.resolve().declaration.qualifiedName!!.asString() == Repeatable::class.qualifiedName
    }

    with(file) {
        if (repeatable) {
            addFunction("get${simpleName.asString()}s") {
                returns(Sequence::class.asClassName().parameterizedBy(dataClass))
                receiver(KSAnnotated::class.asClassName())
                addKdoc(
                    "Returns a [%T] of all [%T] annotations on this [element](%T)",
                    Sequence::class.asClassName(),
                    dataClass,
                    KSAnnotated::class.asClassName()
                )
                addCode("return·%M<%T>()", GET_ANNOTATIONS_BY_TYPE, toClassName())
                addCode(".map(%1L.Companion::%1L)", dataClass.simpleName)
            }
        } else {
            addFunction("get${simpleName.asString()}") {
                returns(dataClass)
                receiver(KSAnnotated::class.asClassName())
                addKdoc("Returns a %T annotation [element](%T)", dataClass, KSAnnotated::class.asClassName())
                addCode("return·%M<%T>()", GET_ANNOTATION_BY_TYPE, toClassName())
                addCode(".let(%1L.Companion::%1L)", dataClass.simpleName)
            }
        }
    }
}
