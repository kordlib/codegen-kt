package dev.kord.codegen.ksp.processor.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import dev.kord.codegen.kotlinpoet.*
import dev.kord.codegen.ksp.annotations.NullIfDefault
import dev.kord.codegen.ksp.annotations.OtherIfDefault
import dev.kord.codegen.ksp.processor.PROCESSOR_ANNOTATION
import dev.kord.codegen.ksp.processor.getOtherIfDefault

fun KSType.isMappedAnnotation(rootType: KSClassDeclaration): Boolean {
    val declaration = declaration
    return if (declaration is KSClassDeclaration && declaration.classKind == ClassKind.ANNOTATION_CLASS) {
        declaration.parentDeclaration == rootType
                || declaration.annotations
            .any { it.annotationType.resolve().declaration.qualifiedName!!.asString() == PROCESSOR_ANNOTATION }
    } else {
        false
    }
}

private fun KSTypeReference.dataClassType(rootType: KSClassDeclaration): TypeName {
    val resolvedType = resolve()
    return when (resolvedType.declaration.qualifiedName!!.asString()) {
        "kotlin.Array" -> LIST.parameterizedBy(element!!.typeArguments.first().type!!.dataClassType(rootType))
        "kotlin.reflect.KClass" -> KSType::class.asClassName()
        "kotlin.Boolean",
        "kotlin.Byte", "kotlin.Short", "kotlin.Int", "kotlin.Long", "kotlin.Char",
        "kotlin.Float", "kotlin.Double",
        "kotlin.String" -> toTypeName()

        else -> {
            val declaration = resolvedType.declaration
            if (declaration is KSClassDeclaration && declaration.classKind == ClassKind.ANNOTATION_CLASS) {
                if (resolvedType.isMappedAnnotation(rootType)
                    || (resolvedType.declaration as? KSClassDeclaration)?.classKind == ClassKind.ENUM_CLASS
                    ) {
                    ClassName("", declaration.simpleName.asString())
                } else {
                    KSAnnotation::class.asClassName()
                }
            } else {
                error("Unexpected type: $this")
            }
        }
    }
}

@OptIn(KspExperimental::class)
fun KSPropertyDeclaration.dataClassType(rootType: KSClassDeclaration): TypeName {
    val notNullType = type.dataClassType(rootType)

    return if (isAnnotationPresent(NullIfDefault::class)) {
        notNullType.copy(nullable = true)
    } else if (isAnnotationPresent(OtherIfDefault::class)) {
        val annotation = getOtherIfDefault()
        val otherField = rootType.getDeclaredProperties().first { it.simpleName.asString() == annotation.name }
        otherField.dataClassType(rootType)
    } else {
        notNullType
    }
}

fun ProcessingContext.dataClassRepresentation() {
    val typeSpec = declaration.dataClassSpec(packageName) {
        addOriginatingKSFile(declaration.containingFile!!)
    }
    file.addType(typeSpec)
}


private fun KSClassDeclaration.dataClassSpec(
    packageName: String,
    additionalBuilder: TypeSpec.Builder.() -> Unit = {}
): TypeSpec {
    return `class`(simpleName.asString()) {
        addKdoc(
            """Data class representation of [%T].
            |@see Companion.%L
        """.trimMargin(), toClassName(), simpleName.asString()
        )
        declarations
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.ANNOTATION_CLASS }
            .forEach {
                addType(it.dataClassSpec(packageName))
            }

        addModifiers(KModifier.DATA)
        primaryConstructor {
            addModifiers(KModifier.PRIVATE)
            this@dataClassSpec.getDeclaredProperties().forEach {
                val name = it.simpleName.asString()
                val type = it.dataClassType(this@dataClassSpec)
                val parameter = addParameter(name, type) {}
                this@`class`.addProperty(name, type) {
                    initializer("%N", parameter)
                }
            }
        }
        additionalBuilder()

        addCompanionObject {
            factoryFunction(packageName)
        }
    }
}


