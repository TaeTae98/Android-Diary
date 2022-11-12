import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("module-core")
    id("hilt-core")
    id("navigation")
    id("compose")
    id("firebase-oauth")
}

android {
    namespace = "${Build.APPLICATION_ID}.share"

    defaultConfig {
        buildConfigField(type = "String", name = "GOOGLE_OAUTH_CLIENT_ID", value = "\"${localProperty("RELEASE_GOOGLE_OAUTH_CLIENT_ID")}\"")
    }

    buildTypes {
        debug {
            buildConfigField(type = "String", name = "GOOGLE_OAUTH_CLIENT_ID", value = "\"${localProperty("DEBUG_GOOGLE_OAUTH_CLIENT_ID")}\"")
        }
    }
}

fun localProperty(key: String) = gradleLocalProperties(rootDir).getProperty(key)
