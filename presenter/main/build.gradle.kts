plugins {
    id("presenter")
}

android {
    namespace = "com.android.diary.presenter.main"
}

dependencies {
    implementation(project(":presenter:memo"))
    implementation(project(":presenter:more"))
}
