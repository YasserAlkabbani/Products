plugins {
    alias(libs.plugins.plana.products.library)
    alias(libs.plugins.plana.products.library.compose)
}

android {
    namespace = "com.plana.products.core.designsystem"

}

dependencies {

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)

    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}