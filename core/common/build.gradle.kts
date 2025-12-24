plugins {
    alias(libs.plugins.plana.products.library)
}

android {
    namespace = "com.plana.products.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}