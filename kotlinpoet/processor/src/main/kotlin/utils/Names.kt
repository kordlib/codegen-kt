@file:OptIn(CodeGenInternal::class, DelicateKotlinPoetApi::class)

package dev.kord.codegen.generator.utils

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.asClassName
import dev.kord.codegen.kotlinpoet.CodeGenDsl
import dev.kord.codegen.kotlinpoet.CodeGenInternal
import dev.kord.codegen.kotlinpoet.asMemberName
import dev.kord.codegen.kotlinpoet.delegate.SubSpecDelegateProvider
import dev.kord.codegen.kotlinpoet.emptyCodeBlock

//private val produceByName: KFunction<SubSpecDelegateProvider<String>> = ::produceByName

val CODEGEN_DSL = CodeGenDsl::class.asClassName()
val EMPTY_CODE_BLOCK = ::emptyCodeBlock.asMemberName()
val PRODUCE_BY_NAME = MemberName("dev.kord.codegen.kotlinpoet.delegate", "produceByName")
val ADD_ANNOTATION = MemberName("dev.kord.codegen.kotlinpoet", "addAnnotation")
val SUB_SPEC_DELEGATE_PROVIDER = SubSpecDelegateProvider::class.asClassName()
