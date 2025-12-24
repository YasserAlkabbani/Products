plugins {
    alias(libs.plugins.plana.products.library)
}

android {
    namespace = "com.plana.products.core.model"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}