package com.plana.products.feature.products.details.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsNavKey(
    val productID: Long
): NavKey