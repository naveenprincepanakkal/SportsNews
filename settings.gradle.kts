pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SportsNews"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:ui")
include(":feature:sportsnews")
