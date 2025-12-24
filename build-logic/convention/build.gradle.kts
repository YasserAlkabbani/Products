import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.plana.products.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {

    plugins {
        register("androidApplication") {
            id = libs.plugins.plana.products.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = libs.plugins.plana.products.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.plana.products.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.plana.products.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeatureApi") {
            id = libs.plugins.plana.products.feature.api.get().pluginId
            implementationClass = "FeatureApiConventionPlugin"
        }
        register("androidFeatureImpl") {
            id = libs.plugins.plana.products.feature.impl.get().pluginId
            implementationClass = "FeatureImplConventionPlugin"
        }

        register("androidHilt") {
            id = libs.plugins.plana.products.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("androidRoom") {
            id = libs.plugins.plana.products.room.get().pluginId
            implementationClass = "RoomConventionPlugin"
        }


    }

}