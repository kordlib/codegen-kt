package dev.kord.codegen.generator.visitors

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSDefaultVisitor

abstract class VisitorBase : KSDefaultVisitor<SymbolProcessorEnvironment, Unit>() {
    final override fun defaultHandler(node: KSNode, data: SymbolProcessorEnvironment) = Unit
}
