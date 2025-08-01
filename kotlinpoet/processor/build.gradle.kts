plugins {
    org.jetbrains.kotlin.jvm
    // If you see Schlaubi using Shadow, you know he's desperate
    id("com.gradleup.shadow") version "9.0.0-rc3"
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xcontext-parameters", "-Xdont-warn-on-error-suppression")
    }
}

val shadow by configurations.getting

dependencies {
    shadow(libs.codegen.kotlinpoet)
    shadow(libs.kotlinpoet.ksp)
    implementation(libs.ksp.api)
    shadow(projects.kotlinpoet.internalAnnotations)
    shadow(projects.ksp.annotations)
    shadow(projects.ksp)
}

tasks {
    jar {
        dependsOn(shadowJar)
        enabled = false
    }

    shadowJar {
        archiveClassifier = ""
        configurations = listOf(shadow)
    }
}
