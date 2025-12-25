package com.plana.products.core.network.retrofit

import com.plana.products.core.network.model.ProductNetwork
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsRetrofit {

    @GET("products")
    suspend fun getProducts(): List<ProductNetwork>

    @GET("products/{id}")
    suspend fun getProductByID(
        @Path("id") productID: Int
    ): ProductNetwork

}