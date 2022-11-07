import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {
    namespace = "com.android.diary"
    compileSdk = Build.COMPILE_SDK

    defaultConfig {
        applicationId = "com.android.diary"
        minSdk = Build.MIN_SDK
        targetSdk = Build.TARGET_SDK
        versionCode = Build.VERSION_CODE
        versionName = Build.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("keystore/debug.keystore")
            keyAlias = localProperty("DEBUG_KEY_ALIAS")
            storePassword = localProperty("DEBUG_KEY_STORE_PASSWORD")
            keyPassword = localProperty("DEBUG_KEY_PASSWORD")
        }

        create("release") {
            storeFile = file("keystore/release.keystore")
            keyAlias = localProperty("RELEASE_KEY_ALIAS")
            storePassword = localProperty("RELEASE_KEY_STORE_PASSWORD")
            keyPassword = localProperty("RELEASE_KEY_PASSWORD")
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")

            applicationIdSuffix = ".debug"
        }

        release {
            signingConfig = signingConfigs.getByName("release")

            isMinifyEnabled = true
            isShrinkResources = true

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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":share"))
    implementation(project(":presenter:main"))

    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")

    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation("com.google.android.material:material:1.7.0")

    implementation(platform("com.google.firebase:firebase-bom:31.0.2"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation("androidx.startup:startup-runtime:1.1.1")

    implementation("androidx.lifecycle:lifecycle-process:2.5.1")

    implementation("com.jakewharton.timber:timber:5.0.1")
}

kapt {
    correctErrorTypes = true
}

fun localProperty(key: String) = gradleLocalProperties(rootDir).getProperty(key)