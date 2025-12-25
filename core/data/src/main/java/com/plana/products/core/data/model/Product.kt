package com.plana.products.core.data.model

import com.plana.products.core.database.model.model.ProductEntity
import com.plana.products.core.model.Product
import com.plana.products.core.network.model.ProductNetwork


fun ProductNetwork.asEntity() = id?.let { id ->
    ProductEntity(
        id = id,
        title = title.orEmpty(),
        price = price ?: 0f,
        description = description.orEmpty(),
        category = category.orEmpty(),
        image = image.orEmpty(),
        rating = rating?.rate ?: 0f,
        ratingCount = rating?.count ?: 0,
    )
}

fun ProductEntity.asModel() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating,
    ratingCount = ratingCount,
)