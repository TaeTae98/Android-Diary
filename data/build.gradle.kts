plugins {
    id("module")
    id("hilt-core")
    id("firebase-oauth")
    id("firebase-database")
    kotlin("kapt")
}

android {
    namespace = "${Build.APPLICATION_ID}.data"

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }

        buildConfigField(type = "String", name = "DIARY_ROOM_DATABASE_NAME", value = "\"diary_room_database.db\"")
    }

    buildTypes {
        debug {
            buildConfigField(type = "String", name = "DIARY_ROOM_DATABASE_NAME", value = "\"diary_room_database_debug.db\"")
        }
    }
}

dependencies {
    implementation("androidx.room:room-runtime:2.5.0-beta01")
    implementation("androidx.room:room-paging:2.5.0-beta01")
    implementation("androidx.room:room-ktx:2.5.0-beta01")
    kapt("androidx.room:room-compiler:2.5.0-beta01")

    implementation("androidx.paging:paging-runtime:3.1.1")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
}
