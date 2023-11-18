import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.KotlinMultiplatform
import org.intellij.lang.annotations.Language
import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMetadataTarget
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

plugins {
    org.jetbrains.dokka
    id("com.vanniktech.maven.publish.base")
    org.jetbrains.kotlinx.`binary-compatibility-validator`
}

tasks {
    withType<AbstractDokkaLeafTask> {
        dokkaSourceSets.configureEach {
            suppressGeneratedFiles = false
            jdkVersion = 8

            val readme = layout.projectDirectory.file("README.md").asFile
            if (readme.exists()) {
                includes.from(readme)
            }

            externalDocumentationLink("https://square.github.io/kotlinpoet/1.x/kotlinpoet/kotlinpoet/")

            sourceLink {
                localDirectory = project.projectDir
                remoteUrl = uri("https://github.com/kordlib/codegen/blob/main/${project.name}").toURL()
                remoteLineSuffix = "#L"
            }

            perPackageOption {
                // Ignore everything from square
                @Language("RegExp")
                matchingRegex = """com\.squareup\..*"""
                suppress = true
            }

            perPackageOption {
                // Ignore everything from square
                @Language("RegExp")
                matchingRegex = """dev.kord.codegen.ksp.processor.*"""
                suppress = true
            }
        }
    }
}

mavenPublishing {
    publishToMavenCentral()
    if (!System.getenv("ORG_GRADLE_PROJECT_signingInMemoryKey").isNullOrBlank()) {
        signAllPublications()
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        configure(KotlinJvm(JavadocJar.Dokka("dokkaHtml")))
    }

    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        configure(KotlinMultiplatform(JavadocJar.Dokka("dokkaHtml")))
    }
}
