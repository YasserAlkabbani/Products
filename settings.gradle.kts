pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Products"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")

include(":feature:products")
include(":feature:products:list")
include(":feature:products:details")

include(":core:common")
include(":core:data")
include(":core:database")
include(":core:network")
include(":core:model")
include(":core:designsystem")
include(":core:ui")
include(":core:navigation")
