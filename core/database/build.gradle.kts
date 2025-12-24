plugins {
    alias(libs.plugins.plana.products.library)
//    alias(libs.plugins.plana.products.room)
//    alias(libs.plugins.plana.products.hilt)
}

android {
    namespace = "com.plana.products.core.database"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}