pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Diary"

include(":app", ":data", ":domain", ":share", ":presenter:ui",)
include(
    ":presenter:main",
    ":presenter:memo",
    ":presenter:calendar",
    ":presenter:more"
)
