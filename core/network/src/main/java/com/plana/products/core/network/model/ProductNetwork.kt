package com.plana.products.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductNetwork(
    @SerialName("id") val id: Int?,
    @SerialName("title") val title: String?,
    @SerialName("price") val price: Float?,
    @SerialName("description") val description: String?,
    @SerialName("category") val category: String?,
    @SerialName("image") val image: String?,
    @SerialName("rating") val rating: RatingNetwork?,
) {
    @Serializable
    data class RatingNetwork(
        @SerialName("rate") val rate: Float?,
        @SerialName("count") val count: Int?,
    )
}