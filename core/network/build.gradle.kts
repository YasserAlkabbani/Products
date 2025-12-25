plugins {
    alias(libs.plugins.plana.products.library)
    alias(libs.plugins.plana.products.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.plana.products.core.network"
}

dependencies {

    api(projects.core.common)

    implementation(libs.squareup.okhttp3.logging)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.serialization)
    implementation(libs.kotlinx.serialization.json)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}