plugins {
    id("org.jetbrains.dokka") version "1.7.20"
}

dependencies {

}

subprojects {
    initDokkaIfNeed()
}

fun Project.initDokkaIfNeed() {
    if (name != "presenter") {
        apply(plugin = "org.jetbrains.dokka")
    }
}

tasks.dokkaHtmlMultiModule.configure {
    // TODO Package Foldering
    // TODO Suppress Face Inherit
    outputDirectory.set(rootDir.resolve("doc"))
}