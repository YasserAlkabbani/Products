plugins {
    alias(libs.plugins.plana.products.library)
//    alias(libs.plugins.plana.products.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.plana.products.core.navigation"

}

dependencies {

    api(libs.androidx.navigation3.runtime)

    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.savedstate.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}