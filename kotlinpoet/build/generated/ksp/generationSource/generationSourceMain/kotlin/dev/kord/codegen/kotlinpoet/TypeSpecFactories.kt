@file:OptIn(ExperimentalContracts::class)

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeSpec
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public typealias TypeSpecBuilderScope = @CodeGenDsl TypeSpec.Builder.() -> Unit

public inline fun `class`(name: String, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.classBuilder(name).apply(block).build()
}

public inline fun `class`(className: ClassName, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.classBuilder(className).apply(block).build()
}

public inline fun expectClass(name: String, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.expectClassBuilder(name).apply(block).build()
}

public inline fun expectClass(className: ClassName, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.expectClassBuilder(className).apply(block).build()
}

public inline fun valueClass(name: String, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.valueClassBuilder(name).apply(block).build()
}

public inline fun `object`(name: String, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.objectBuilder(name).apply(block).build()
}

public inline fun `object`(className: ClassName, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.objectBuilder(className).apply(block).build()
}

public inline fun companionObject(name: String? = null, block: TypeSpecBuilderScope = {}):
        TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.companionObjectBuilder(name).apply(block).build()
}

public inline fun `interface`(name: String, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.interfaceBuilder(name).apply(block).build()
}

public inline fun `interface`(className: ClassName, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.interfaceBuilder(className).apply(block).build()
}

public inline fun funInterface(name: String, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.funInterfaceBuilder(name).apply(block).build()
}

public inline fun funInterface(className: ClassName, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.funInterfaceBuilder(className).apply(block).build()
}

public inline fun `enum`(name: String, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.enumBuilder(name).apply(block).build()
}

public inline fun `enum`(className: ClassName, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.enumBuilder(className).apply(block).build()
}

public inline fun anonymousClass(block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.anonymousClassBuilder().apply(block).build()
}

public inline fun `annotation`(name: String, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.annotationBuilder(name).apply(block).build()
}

public inline fun `annotation`(className: ClassName, block: TypeSpecBuilderScope = {}): TypeSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return TypeSpec.annotationBuilder(className).apply(block).build()
}
