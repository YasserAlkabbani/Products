package com.plana.products.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("price") val price: Float,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("category") val category: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("rating") val rating: Float,
    @ColumnInfo("rating_count") val ratingCount: Int,
)