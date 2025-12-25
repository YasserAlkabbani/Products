package com.plana.products.core.model

data class Product(
    val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val image: String,
    val rating: Float,
    val ratingCount: Int,
)