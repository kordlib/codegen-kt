import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest

plugins {
    `code-generator`
    `kord-publishing`
}

dependencies {
    api(libs.kotlinpoet)
    implementation(kotlin("reflect"))
    compileOnly(projects.kotlinpoet.internalAnnotations)
    ksp(projects.kotlinpoet.processor)

    testImplementation(kotlin("test-junit5"))
}

kotlin {
    sourceSets {
        all {
            languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
        }
    }
}

codeGeneration {
    fromVersionCatalog(libs.kotlinpoet)
    packageName = "dev.kord.codegen.kotlinpoet"
}

ksp {
    arg("ignore-reification", listOf("Taggable.kt").joinToString(" "))
}

tasks {
    withType<KotlinJvmTest> {
        useJUnitPlatform()
    }
}

apiValidation {
    ignoredProjects.add("internal-annotations")
    ignoredProjects.add("processor")
}
