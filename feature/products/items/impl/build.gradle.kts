plugins {
    alias(libs.plugins.plana.products.feature.impl)
    alias(libs.plugins.plana.products.library.compose)
}

android {
    namespace = "com.plana.products.feature.products.items.impl"
}

dependencies {

    implementation(projects.core.data)
    implementation(projects.feature.products.items.api)
    implementation(projects.feature.products.details.api)

    implementation(libs.androidx.paging.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}