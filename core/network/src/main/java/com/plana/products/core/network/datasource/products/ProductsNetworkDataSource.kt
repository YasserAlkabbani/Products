package com.plana.products.core.network.datasource.products

import com.plana.products.core.network.model.ProductNetwork

interface ProductsNetworkDataSource {

    suspend fun getProducts(): List<ProductNetwork>

    suspend fun getProductByID(productID: Int): ProductNetwork

}