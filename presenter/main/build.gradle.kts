plugins {
    id("presenter")
}

android {
    namespace = "${Build.APPLICATION_ID}.presenter.main"
}

dependencies {
    implementation(project(":presenter:memo"))
    implementation(project(":presenter:calendar"))
    implementation(project(":presenter:more"))
}
