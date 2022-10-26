plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.diary"
    compileSdk = Build.COMPILE_SDK

    defaultConfig {
        applicationId = "com.android.diary"
        minSdk =Build.MIN_SDK
        targetSdk =Build.TARGET_SDK
        versionCode = Build.VERSION_CODE
        versionName = Build.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled =false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Build.SOURCE_COMPATIBILITY
        targetCompatibility = Build.TARGET_COMPATIBILITY
    }
    kotlinOptions {
        jvmTarget = Build.JVM_TARGET
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":presenter:main"))

    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("com.google.android.material:material:1.7.0")
}