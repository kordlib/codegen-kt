import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest

plugins {
    `code-generator`
    `kord-publishing`
}

dependencies {
    commonMainApi(libs.kotlinpoet)
    commonMainImplementation(kotlin("reflect"))
    commonMainCompileOnly(projects.kspAnnotations)
    kspCommonMainMetadata(projects.codeProcessor)

    commonTestImplementation(kotlin("test-junit5"))
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

//    dokkaHtmlMultiModule {
//
//    }
}
