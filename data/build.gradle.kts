plugins {
    id("module")
    id("hilt-core")
    kotlin("kapt")
}

android {
    namespace = "com.android.diary.data"

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }

        buildConfigField(type = "String", name = "DIARY_ROOM_DATABSE_NAME", value = "\"diary_room_database.db\"")
    }

    buildTypes {
        debug {
            buildConfigField(type = "String", name = "DIARY_ROOM_DATABSE_NAME", value = "\"diary_room_database_debug.db\"")
        }
    }
}

dependencies {
    implementation("androidx.room:room-runtime:2.5.0-beta01")
    implementation("androidx.room:room-paging:2.5.0-beta01")
    implementation("androidx.room:room-ktx:2.5.0-beta01")
    kapt("androidx.room:room-compiler:2.5.0-beta01")
}