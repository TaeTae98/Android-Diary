plugins {
    id("module")
    id("compose")
    id("hilt")
}

dependencies {
    implementation(project(":presenter:ui"))
}