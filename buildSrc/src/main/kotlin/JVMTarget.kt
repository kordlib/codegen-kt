import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

fun Project.configureJVMTarget() {
    plugins.withId("org.gradle.java") {
        configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_1_8
        }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        the<KotlinJvmProjectExtension>().compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
        }
    }

    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        the<KotlinMultiplatformExtension>().targets.withType<KotlinJvmTarget> {
            compilations.all {
                compileTaskProvider.configure {
                    compilerOptions {
                        jvmTarget = JvmTarget.JVM_1_8
                    }
                }
            }
        }
    }
}
