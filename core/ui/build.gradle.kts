plugins {
    alias(libs.plugins.plana.products.library)
    alias(libs.plugins.plana.products.library.compose)
}

android {
    namespace = "com.plana.products.core.ui"
}

dependencies {

//    api(projects.core.design-system)
    api(projects.core.model)

    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}