plugins {
    id("module")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    implementation("androidx.compose.animation:animation:1.3.0")
    implementation("androidx.compose.foundation:foundation:1.3.0")
    implementation("androidx.compose.material:material:1.3.0")
    implementation("androidx.compose.material:material-icons-core:1.3.0")
    implementation("androidx.compose.material:material-icons-extended:1.3.0")
    implementation("androidx.compose.material3:material3:1.0.0")
    implementation("androidx.compose.runtime:runtime:1.3.0")
    implementation("androidx.compose.ui:ui:1.3.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    debugImplementation("androidx.compose.ui:ui-tooling:1.3.0")
}