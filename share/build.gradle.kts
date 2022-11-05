import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("module-core")
    id("hilt-core")
    id("navigation")
    id("compose")
    id("firebase-oauth")
}

android {
    namespace = "com.diary.android.share"

    defaultConfig {
        buildConfigField(type = "String", name = "GOOGLE_OAUTH_CLIENT_ID", value = "\"${localProperty("DEBUG_GOOGLE_OAUTH_CLIENT_ID")}\"")
    }
}

fun localProperty(key: String) = gradleLocalProperties(rootDir).getProperty(key)