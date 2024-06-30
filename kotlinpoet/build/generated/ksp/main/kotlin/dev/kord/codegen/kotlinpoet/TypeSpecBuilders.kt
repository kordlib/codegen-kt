package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeSpecHolder
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun TypeSpecHolder.Builder<*>.addAnnotationClass(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`annotation`(className, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addAnnotationClass(name: String,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`annotation`(name, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addClass(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`class`(className, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addClass(name: String, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`class`(name, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addCompanionObject(name: String? = null,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.companionObject(name, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addEnum(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`enum`(className, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addEnum(name: String, block: TypeSpecBuilderScope = {}):
    TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`enum`(name, block).also(::addType)
}

@Deprecated(
  replaceWith = ReplaceWith(expression =
      "TypeSpec.classBuilder(className).addModifiers(KModifier.EXPECT)"),
  message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
  level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
public inline fun TypeSpecHolder.Builder<*>.addExpectClass(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.expectClass(className, block).also(::addType)
}

@Deprecated(
  replaceWith = ReplaceWith(expression =
      "TypeSpec.classBuilder(name).addModifiers(KModifier.EXPECT)"),
  message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
  level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
public inline fun TypeSpecHolder.Builder<*>.addExpectClass(name: String, block: TypeSpecBuilderScope
    = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.expectClass(name, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addFunInterface(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.funInterface(className, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addFunInterface(name: String,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.funInterface(name, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addInterface(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`interface`(className, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addInterface(name: String, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`interface`(name, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addObject(className: ClassName,
    block: TypeSpecBuilderScope = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`object`(className, block).also(::addType)
}

public inline fun TypeSpecHolder.Builder<*>.addObject(name: String, block: TypeSpecBuilderScope =
    {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.`object`(name, block).also(::addType)
}

@Deprecated(
  replaceWith = ReplaceWith(expression =
      "TypeSpec.classBuilder(name).addModifiers(KModifier.VALUE)"),
  message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
  level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
public inline fun TypeSpecHolder.Builder<*>.addValueClass(name: String, block: TypeSpecBuilderScope
    = {}): TypeSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return TypeSpec.valueClass(name, block).also(::addType)
}

public inline fun TypeSpec.Builder.primaryConstructor(block: FunSpecBuilderScope = {}): FunSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return FunSpec.`constructor`(block).also(::primaryConstructor)
}
