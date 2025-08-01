package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.KOperator
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.asClassName
import kotlin.Boolean
import kotlin.String

public inline fun <reified E> MemberName.copy(
  packageName: String = "",
  simpleName: String = "",
  `operator`: KOperator? = null,
  isExtension: Boolean = false,
): MemberName = copy(packageName, E::class.asClassName(), simpleName, `operator`, isExtension)
