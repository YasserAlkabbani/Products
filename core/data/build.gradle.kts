plugins {
    alias(libs.plugins.plana.products.library)
//    alias(libs.plugins.plana.products.hilt)
}

android {
    namespace = "com.plana.products.core.data"
}

dependencies {

    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.network)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}