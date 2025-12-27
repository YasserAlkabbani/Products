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
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.ui.tooling)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}