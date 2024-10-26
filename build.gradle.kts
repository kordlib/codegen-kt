plugins {
    org.jetbrains.kotlin.jvm apply false
    org.jetbrains.dokka
}

allprojects {
    group = "dev.kord.codegen"
    version = "main-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    configurations.all {
        val conf = this
        conf.resolutionStrategy.eachDependency {
            if (requested.group == "dev.kord.codegen") {
                when (requested.name) {
                    "kotlinpoet" -> useVersion("main-20240811.165308-18")
                    "ksp" -> useVersion("main-20240811.165308-20")
                }
            }
        }
    }
}

dependencies {
//    dokka(projects.ksp.annotations)
    dokka(projects.ksp)
    dokka(projects.kotlinpoet)
}

subprojects {
    configureJVMTarget()
}

tasks {
    dokkaGeneratePublicationHtml {

    }
}
