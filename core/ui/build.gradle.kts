plugins {
    alias(libs.plugins.plana.products.library)
    alias(libs.plugins.plana.products.library.compose)
}

android {
    namespace = "com.plana.products.core.ui"
}

dependencies {

    api(projects.core.designsystem)
    api(projects.core.model)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}