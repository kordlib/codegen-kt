pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.6.0"
}

rootProject.name = "codegen-kt"

include("code-processor", "kotlinpoet", "ksp", "ksp-processor", "ksp-annotations", "bom")
