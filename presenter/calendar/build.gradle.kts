plugins {
    id("presenter")
}

android {
    namespace = "${Build.APPLICATION_ID}.presenter.calendar"
}

dependencies {
    implementation(project(":presenter:memo"))
    implementation(project(":presenter:more"))
}
