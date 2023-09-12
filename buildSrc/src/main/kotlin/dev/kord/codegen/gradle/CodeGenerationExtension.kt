package dev.kord.codegen.gradle

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderConvertible
import org.gradle.kotlin.dsl.assign

interface CodeGenerationExtension {
    val dependency: Property<String>
    val packageName: Property<String>

    fun fromVersionCatalog(provider: Provider<MinimalExternalModuleDependency>) {
        dependency = provider.map { "${it.group}:${it.name}:${it.version}" }
    }

    fun fromVersionCatalog(convertible: ProviderConvertible<MinimalExternalModuleDependency>) =
        fromVersionCatalog(convertible.asProvider())
}
