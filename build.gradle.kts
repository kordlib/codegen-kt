plugins {
    org.jetbrains.kotlin.jvm apply false
    org.jetbrains.dokka
}

allprojects {
    group = "dev.kord.codegen"
    version = "1.0.0"

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    configurations.all {
        val conf = this
        conf.resolutionStrategy.eachDependency {
            if (requested.group == "dev.kord.codegen") {
                when (requested.name) {
                    "kotlinpoet" -> useVersion("main-20241026.144617-21")
                    "ksp-processor" -> useVersion("main-20241026.144617-22")
                    "ksp" -> useVersion("main-20241026.144617-23")
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
