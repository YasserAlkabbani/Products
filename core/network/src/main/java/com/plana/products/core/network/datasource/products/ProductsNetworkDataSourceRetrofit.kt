package com.plana.products.core.network.datasource.products

import com.plana.products.core.network.model.ProductNetwork
import com.plana.products.core.network.retrofit.ProductsRetrofit
import javax.inject.Inject

class ProductsNetworkDataSourceRetrofit @Inject constructor(
    val productsRetrofit: ProductsRetrofit,
) : ProductsNetworkDataSource {

    override suspend fun getProducts(): List<ProductNetwork> =
        productsRetrofit.getProducts()

    override suspend fun getProductByID(productID: Int): ProductNetwork =
        productsRetrofit.getProductByID(productID)

}