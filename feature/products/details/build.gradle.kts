plugins {
    alias(libs.plugins.plana.products.feature.impl)
}

android {
    namespace = "com.plana.products.feature.products.details"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}