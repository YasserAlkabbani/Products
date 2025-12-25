plugins {
    alias(libs.plugins.plana.products.application)
    alias(libs.plugins.plana.products.application.compose)
    alias(libs.plugins.plana.products.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {

    namespace = "com.plana.products"

    defaultConfig {
        applicationId = "com.plana.products"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {

    implementation(projects.feature.products.items.api)
    implementation(projects.feature.products.items.impl)
    implementation(projects.feature.products.details.api)
    implementation(projects.feature.products.details.impl)

    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.model)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines)

    ksp(libs.google.dagger.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

}