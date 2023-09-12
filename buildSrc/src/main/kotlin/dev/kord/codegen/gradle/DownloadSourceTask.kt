package dev.kord.codegen.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.result.ResolvedArtifactResult
import org.gradle.api.file.Directory
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderConvertible
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.JvmLibrary
import org.gradle.kotlin.dsl.*
import org.gradle.language.base.artifact.SourcesArtifact

abstract class DownloadSourceTask : DefaultTask() {
    @get:Input
    abstract val dependency: Property<String>

    @OutputDirectory
    val destinationDirectory: Provider<Directory> = project.layout.buildDirectory.dir("generation-source")

    @TaskAction
    fun download() {
        val dependency = project.dependencies.create(dependency.get()) {
            isTransitive = true
        }

        val artifacts = project.dependencies.createArtifactResolutionQuery()
            .forModule(dependency.group as String, dependency.name, dependency.version as String)
            .withArtifacts(JvmLibrary::class.java, SourcesArtifact::class.java)
            .execute()

        val sourceFile = (artifacts.resolvedComponents.single()
            .getArtifacts(SourcesArtifact::class.java)
            .single() as ResolvedArtifactResult)
            .file

        with(project) {
            copy {
                from(zipTree(sourceFile)) {
                    // This file does not compile on 1.9.10 for some reason
                    // We don't really need it's contents anyways, so we just exclude it
                    exclude("**/JvmAnnotations.kt")
                }
                into(destinationDirectory)
            }
        }
    }
}
