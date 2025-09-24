plugins {
    org.jetbrains.kotlin.jvm apply false
    org.jetbrains.dokka
}

allprojects {
    group = "dev.kord.codegen"
    version = "1.0.2"

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    configurations.all {
        val conf = this
        conf.resolutionStrategy.eachDependency {
            if (requested.group == "dev.kord.codegen") {
                when (requested.name) {
                    "kotlinpoet" -> useVersion(libs.versions.codegen.kt.get())
                    "ksp-processor" -> useVersion(libs.versions.codegen.kt.get())
                    "ksp" -> useVersion(libs.versions.codegen.kt.get())
                }
            }
        }
    }
}

dependencies {
    dokka(projects.ksp.annotations)
    dokka(projects.ksp)
    dokka(projects.kotlinpoet)
}

subprojects {
    configureJVMTarget()
}

tasks {
    dokkaGeneratePublicationHtml {
        outputDirectory = layout.projectDirectory.dir("docs/api")
    }
}
