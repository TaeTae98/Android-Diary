import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("module-core")
    id("hilt-core")
    id("navigation")
    id("compose")
    id("firebase")
}

android {
    namespace = "com.diary.android.share"

    defaultConfig {
        buildConfigField(type = "String", name = "GOOGLE_OAUTH_CLIENT_ID", value = "\"${localProperty("DEBUG_GOOGLE_OAUTH_CLIENT_ID")}\"")
        buildConfigField(type = "String", name = "HI", value = "\"${localProperty("HI")}\"")
    }
}

fun localProperty(key: String) = gradleLocalProperties(rootDir).getProperty(key)