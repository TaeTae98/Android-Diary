plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Build.SOURCE_COMPATIBILITY
    targetCompatibility = Build.TARGET_COMPATIBILITY
}

dependencies {
    implementation("javax.inject:javax.inject:1")
    implementation("androidx.paging:paging-common:3.1.1")
}