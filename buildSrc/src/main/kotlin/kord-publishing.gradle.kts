import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.KotlinMultiplatform
import gradle.kotlin.dsl.accessors._8d1ef46afdac024fc616c6e49cc7c389.kotlin
import org.intellij.lang.annotations.Language
import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask
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
}

afterEvaluate {
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        if (kotlin.targets.any { it !is KotlinJvmTarget && it !is KotlinMetadataTarget }) {
            mavenPublishing.configure(KotlinMultiplatform(JavadocJar.Dokka("dokkaHtml")))
            return@withId
        }
        val dokkaJar by tasks.registering(Jar::class) {
            from(tasks.named("dokkaHtml"))
            archiveClassifier = "javadoc"
        }


        // We're kinda abusing the KMP target system here
        // We make an extra jvm target for the kotlinpoet source to run ksp on it, however we don't want to publish
        // that target, so we emulate the "jvm" target being a normal Kotlin/JVM project and publish it that way
        afterEvaluate {
            publishing.publications.create<MavenPublication>("maven") {
                from(kotlin.targets.getByName("jvm").components.first())
                artifact(dokkaJar)
            }
        }

        // Remove auto-generated KMP publications
        tasks {
            withType<PublishToMavenLocal> {
                enabled = name == "publishMavenPublicationToMavenLocal"
            }

            withType<PublishToMavenRepository> {
                enabled = name == "publishMavenPublicationToMavenRepository"
                    || name == "publishMavenPublicationToMavenCentralRepository"
            }

            // Theoretically, this makes us loose information, however, that information is not relevant, since this is
            // pretty much only a JVM lib targeting JVM 1.8 (so validation of that is unnecessary)
            withType<GenerateModuleMetadata> {
                enabled = false
            }
        }
    }
}
