package dev.kord.codegen.generator.utils

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName

val CODEGEN_DSL = ClassName("dev.kord.codegen.kotlinpoet", "CodeGenDsl")
val EMPTY_CODE_BLOCK = MemberName("dev.kord.codegen.kotlinpoet", "emptyCodeBlock")
val PRODUCE_BY_NAME = MemberName("dev.kord.codegen.kotlinpoet.delegate", "produceByName")
val SUB_SPEC_DELEGATE_PROVIDER = ClassName("dev.kord.codegen.kotlinpoet.delegate", "SubSpecDelegateProvider")
