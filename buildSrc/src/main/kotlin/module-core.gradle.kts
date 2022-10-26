plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.diary.presenter.ui"
    compileSdk = Build.COMPILE_SDK

    defaultConfig {
        minSdk = Build.MIN_SDK
        targetSdk = Build.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Build.SOURCE_COMPATIBILITY
        targetCompatibility = Build.TARGET_COMPATIBILITY
    }
    kotlinOptions {
        jvmTarget = Build.JVM_TARGET
    }
}

dependencies {
    implementation(project(":domain"))
}
