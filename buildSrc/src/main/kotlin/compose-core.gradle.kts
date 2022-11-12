plugins {
    id("module-core")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
}

dependencies {
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation("androidx.compose.animation:animation:1.3.1")

    implementation("androidx.compose.foundation:foundation:1.3.1")

    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.compose.material:material-icons-core:1.3.1")
    implementation("androidx.compose.material:material-icons-extended:1.3.1")
    implementation("androidx.compose.material3:material3:1.0.1")

    implementation("androidx.compose.runtime:runtime:1.3.1")

    implementation("androidx.compose.ui:ui:1.3.1")
    implementation("androidx.compose.ui:ui-tooling:1.3.1")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    implementation("androidx.paging:paging-compose:1.0.0-alpha17")
}