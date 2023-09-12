@file:OptIn(ExperimentalContracts::class)

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun TypeSpec.Builder.addClass(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `class`(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addClass(className: ClassName, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `class`(className, block).also(::addType)
}

public inline fun TypeSpec.Builder.addExpectClass(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return expectClass(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addExpectClass(className: ClassName, block: TypeSpecBuilderScope
    = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return expectClass(className, block).also(::addType)
}

public inline fun TypeSpec.Builder.addValueClass(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return valueClass(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addObject(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `object`(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addObject(className: ClassName, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `object`(className, block).also(::addType)
}

public inline fun TypeSpec.Builder.addCompanionObject(name: String? = null,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return companionObject(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addInterface(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `interface`(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addInterface(className: ClassName, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `interface`(className, block).also(::addType)
}

public inline fun TypeSpec.Builder.addFunInterface(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return funInterface(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addFunInterface(className: ClassName, block: TypeSpecBuilderScope
    = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return funInterface(className, block).also(::addType)
}

public inline fun TypeSpec.Builder.addEnum(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `enum`(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addEnum(className: ClassName, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `enum`(className, block).also(::addType)
}

public inline fun TypeSpec.Builder.addAnonymousClass(block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return anonymousClass(block).also(::addType)
}

public inline fun TypeSpec.Builder.addAnnotationClass(name: String, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `annotation`(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.addAnnotationClass(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `annotation`(className, block).also(::addType)
}

public inline fun FileSpec.Builder.addClass(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `class`(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addClass(className: ClassName, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `class`(className, block).also(::addType)
}

public inline fun FileSpec.Builder.addExpectClass(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return expectClass(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addExpectClass(className: ClassName, block: TypeSpecBuilderScope
    = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return expectClass(className, block).also(::addType)
}

public inline fun FileSpec.Builder.addValueClass(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return valueClass(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addObject(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `object`(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addObject(className: ClassName, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `object`(className, block).also(::addType)
}

public inline fun FileSpec.Builder.addCompanionObject(name: String? = null,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return companionObject(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addInterface(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `interface`(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addInterface(className: ClassName, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `interface`(className, block).also(::addType)
}

public inline fun FileSpec.Builder.addFunInterface(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return funInterface(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addFunInterface(className: ClassName, block: TypeSpecBuilderScope
    = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return funInterface(className, block).also(::addType)
}

public inline fun FileSpec.Builder.addEnum(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `enum`(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addEnum(className: ClassName, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `enum`(className, block).also(::addType)
}

public inline fun FileSpec.Builder.addAnonymousClass(block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return anonymousClass(block).also(::addType)
}

public inline fun FileSpec.Builder.addAnnotationClass(name: String, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `annotation`(name, block).also(::addType)
}

public inline fun FileSpec.Builder.addAnnotationClass(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `annotation`(className, block).also(::addType)
}

public inline fun TypeSpec.Builder.primaryConstructor(block: FunSpecBuilderScope = {}): FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return `constructor`(block).also(::primaryConstructor)
}
