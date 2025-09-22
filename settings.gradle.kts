pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "codegen-kt"

include(
    ":kotlinpoet:annotations",
    ":kotlinpoet:processor",
    ":kotlinpoet:kotlinpoet-source",
    ":kotlinpoet",
    ":ksp:annotations",
    ":ksp:processor",
    ":ksp"
)

// For some reason not doing this makes Gradle interpret both "annotations" modules the same and causes circular
// dependencies
project(":kotlinpoet:annotations").name = "internal-annotations"

