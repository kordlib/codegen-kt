package dev.kord.codegen.ksp.processor.generator

import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import dev.kord.codegen.ksp.processor.NULL_IF_DEFAULT
import dev.kord.codegen.ksp.processor.PROCESSOR_ANNOTATION
import java.util.logging.Logger

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
                if (resolvedType.isMappedAnnotation(rootType)) {
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

fun KSPropertyDeclaration.dataClassType(rootType: KSClassDeclaration): TypeName {
    val notNullType = type.dataClassType(rootType)

    return if (annotations.any { it.annotationType.resolve().declaration.qualifiedName!!.asString() == NULL_IF_DEFAULT }) {
        notNullType.copy(nullable = true)
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
    return TypeSpec.classBuilder(simpleName.asString()).apply {
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
        val primaryConstructor = FunSpec.constructorBuilder()
            .addModifiers(KModifier.PRIVATE)
        this@dataClassSpec.getDeclaredProperties().forEach {
            val name = it.simpleName.asString()
            val type = it.dataClassType(this@dataClassSpec)
            val parameter = ParameterSpec.builder(name, type).build()
            primaryConstructor.addParameter(parameter)
            val property = PropertySpec.builder(name, type).apply {
                initializer("%N", parameter)
            }
            addProperty(property.build())
        }
        primaryConstructor(primaryConstructor.build())
        additionalBuilder()

        val companion = TypeSpec.companionObjectBuilder()
            .addFunction(factoryFunction(packageName))
            .build()
        addType(companion)
    }.build()
}


