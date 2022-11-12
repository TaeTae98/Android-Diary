import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    id("org.jetbrains.dokka") version "1.7.20"
    id("com.diffplug.spotless") version "6.11.0"
}

subprojects {
    initDokka()
    initSpotless()
}

fun Project.initDokka() {
    if (name != "presenter") {
        apply(plugin = "org.jetbrains.dokka")
    }
}

fun Project.initSpotless() {
    if (name != "presenter") {
        apply(plugin = "com.diffplug.spotless")

        configure<SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                ktlint("0.43.2")
                indentWithSpaces()
                endWithNewline()
            }
            kotlinGradle {
                target("**/*.gradle.kts")
                ktlint("0.43.2")
                indentWithSpaces()
                endWithNewline()
            }
        }

        afterEvaluate {
            if (plugins.hasPlugin("java-library")) {
                tasks.getByPath("classes").dependsOn(tasks.spotlessApply)
            } else {
                tasks.getByPath("preBuild").dependsOn(tasks.spotlessApply)
            }
        }
    }
}

tasks.dokkaHtmlMultiModule.configure {
    // TODO Package Foldering
    // TODO Suppress Face Inherit
    outputDirectory.set(rootDir.resolve("doc"))
}
