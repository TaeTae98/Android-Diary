plugins {
    id("module-core")
    id("compose-core")
}

kotlin.sourceSets.all {
    languageSettings.optIn("androidx.compose.animation.ExperimentalAnimationApi")
}

dependencies {
    implementation("com.google.accompanist:accompanist-navigation-animation:0.27.0")
    implementation("com.google.accompanist:accompanist-navigation-material:0.27.0")
}