package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeSpec
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public typealias TypeSpecBuilderScope = @CodeGenDsl TypeSpec.Builder.() -> Unit

public inline fun TypeSpec.Companion.`class`(name: String, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.classBuilder(name).apply(block).build()
}

public inline fun TypeSpec.Companion.`class`(className: ClassName, block: TypeSpecBuilderScope =
        {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.classBuilder(className).apply(block).build()
}

@Deprecated(
    message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
    replaceWith = ReplaceWith(expression =
            "TypeSpec.classBuilder(name).addModifiers(KModifier.EXPECT)"),
    level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
public inline fun TypeSpec.Companion.expectClass(name: String, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.expectClassBuilder(name).apply(block).build()
}

@Deprecated(
    message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
    replaceWith = ReplaceWith(expression =
            "TypeSpec.classBuilder(className).addModifiers(KModifier.EXPECT)"),
    level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
public inline fun TypeSpec.Companion.expectClass(className: ClassName, block: TypeSpecBuilderScope =
        {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.expectClassBuilder(className).apply(block).build()
}

@Deprecated(
    message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
    replaceWith = ReplaceWith(expression =
            "TypeSpec.classBuilder(name).addModifiers(KModifier.VALUE)"),
    level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
public inline fun TypeSpec.Companion.valueClass(name: String, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.valueClassBuilder(name).apply(block).build()
}

public inline fun TypeSpec.Companion.`object`(name: String, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.objectBuilder(name).apply(block).build()
}

public inline fun TypeSpec.Companion.`object`(className: ClassName, block: TypeSpecBuilderScope =
        {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.objectBuilder(className).apply(block).build()
}

public inline fun TypeSpec.Companion.companionObject(name: String? = null,
        block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.companionObjectBuilder(name).apply(block).build()
}

public inline fun TypeSpec.Companion.`interface`(name: String, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.interfaceBuilder(name).apply(block).build()
}

public inline fun TypeSpec.Companion.`interface`(className: ClassName, block: TypeSpecBuilderScope =
        {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.interfaceBuilder(className).apply(block).build()
}

public inline fun TypeSpec.Companion.funInterface(name: String, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.funInterfaceBuilder(name).apply(block).build()
}

public inline fun TypeSpec.Companion.funInterface(className: ClassName, block: TypeSpecBuilderScope
        = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.funInterfaceBuilder(className).apply(block).build()
}

public inline fun TypeSpec.Companion.`enum`(name: String, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.enumBuilder(name).apply(block).build()
}

public inline fun TypeSpec.Companion.`enum`(className: ClassName, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.enumBuilder(className).apply(block).build()
}

public inline fun TypeSpec.Companion.anonymousClass(block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.anonymousClassBuilder().apply(block).build()
}

public inline fun TypeSpec.Companion.`annotation`(name: String, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.annotationBuilder(name).apply(block).build()
}

public inline fun TypeSpec.Companion.`annotation`(className: ClassName, block: TypeSpecBuilderScope
        = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.annotationBuilder(className).apply(block).build()
}
