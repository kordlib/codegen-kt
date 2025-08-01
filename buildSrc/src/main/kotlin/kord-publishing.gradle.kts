import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.KotlinMultiplatform
import org.intellij.lang.annotations.Language
import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask

plugins {
    org.jetbrains.dokka
    id("com.vanniktech.maven.publish.base")
}
dokka {
    dokkaSourceSets.configureEach {
        suppressGeneratedFiles = false
        jdkVersion = 8

        val readme = layout.projectDirectory.file("README.md").asFile
        if (readme.exists()) {
            includes.from(readme)
        }

        externalDocumentationLinks {
            create("square/kotlinpoet") {
                url("https://square.github.io/kotlinpoet/1.x/kotlinpoet/")
                packageListUrl("https://gist.githubusercontent.com/DRSchlaubi/8cf8ab7628b6926e1520fa046ced9c4a/raw/4252fe08441a40171d64cd633abac46729c33724/package-list")
            }
        }

        sourceLink {
            localDirectory = project.projectDir
            remoteUrl = uri("https://github.com/kordlib/codegen/blob/main/${project.name}")
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

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    if (!System.getenv("ORG_GRADLE_PROJECT_signingInMemoryKey").isNullOrBlank()) {
        signAllPublications()
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        configure(KotlinJvm(JavadocJar.Dokka("dokkaGeneratePublicationHtml")))
    }

    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        configure(KotlinMultiplatform(JavadocJar.Dokka("dokkaGeneratePublicationHtml")))
    }

    pom {
        name = "codegen.kt"
        description = "Utitlities for generating Kotlin code"
        url = "https://github.com/kordlib/codegen-kt"

        organization {
            name = "Kord"
            url = "https://github.com/kordlib"
        }

        developers {
            developer {
                name = "The Kord Team"
            }
        }

        issueManagement {
            system = "GitHub"
            url = "https://github.com/kordlib/kord/issues"
        }

        licenses {
            license {
                name = "MIT"
                url = "https://opensource.org/licenses/MIT"
            }
        }

        scm {
            connection = "scm:git:ssh://github.com/kordlib/codegen-kt.git"
            developerConnection = "scm:git:ssh://git@github.com:kordlib/codegen-kt.git"
            url = "https://github.com/kordlib/codegen-kt.git"
        }
    }
}
