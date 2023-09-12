@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
@file:OptIn(CodeGenInternal::class)

package dev.kord.codegen.generator.utils

import com.squareup.kotlinpoet.asClassName
import dev.kord.codegen.kotlinpoet.CodeGenDsl
import dev.kord.codegen.kotlinpoet.CodeGenInternal
import dev.kord.codegen.kotlinpoet.asMemberName
import dev.kord.codegen.kotlinpoet.delegate.SubSpecDelegateProvider
import dev.kord.codegen.kotlinpoet.delegate.produceByName
import dev.kord.codegen.kotlinpoet.emptyCodeBlock
import kotlin.reflect.KFunction

private val produceByName: KFunction<SubSpecDelegateProvider<String>> = ::produceByName

val CODEGEN_DSL = CodeGenDsl::class.asClassName()
val EMPTY_CODE_BLOCK = ::emptyCodeBlock.asMemberName()
val PRODUCE_BY_NAME = produceByName.asMemberName()
val SUB_SPEC_DELEGATE_PROVIDER = SubSpecDelegateProvider::class.asClassName()
