plugins {
    org.jetbrains.kotlin.jvm apply false
    org.jetbrains.dokka
}

allprojects {
    group = "dev.kord.codegen"
    version = "main-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

tasks {
    dokkaHtmlMultiModule {
        outputDirectory = layout.projectDirectory.dir("docs/api")
    }
}
