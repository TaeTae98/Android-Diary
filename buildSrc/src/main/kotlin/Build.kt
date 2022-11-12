import org.gradle.api.JavaVersion

object Build {
    const val APPLICATION_ID = "com.diary.android"

    const val MIN_SDK = 30
    const val TARGET_SDK = 33
    const val COMPILE_SDK = 33

    const val VERSION_CODE = 10000
    const val VERSION_NAME = "1.0.0"

    val SOURCE_COMPATIBILITY = JavaVersion.VERSION_11
    val TARGET_COMPATIBILITY = JavaVersion.VERSION_11
    val JVM_TARGET = JavaVersion.VERSION_11.toString()
}