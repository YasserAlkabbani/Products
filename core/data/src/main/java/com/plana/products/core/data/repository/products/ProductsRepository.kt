package com.plana.products.core.data.repository.products

import androidx.paging.PagingData
import com.plana.products.core.common.request.RequestState
import com.plana.products.core.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun refreshProducts(): Flow<RequestState<Unit>>

    fun refreshProductByID(productID: Int): Flow<RequestState<Unit>>

    fun getProductsCategories(): Flow<List<String>>

    fun getProductsByCategory(category: String?): Flow<PagingData<Product>>

    fun getProductByID(productID: Int): Flow<Product>

}