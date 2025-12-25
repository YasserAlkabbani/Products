plugins {
    alias(libs.plugins.plana.products.feature.api)
}

android {
    namespace = "com.plana.products.feature.products.details.api"

}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}