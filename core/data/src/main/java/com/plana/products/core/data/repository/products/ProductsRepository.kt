package com.plana.products.core.data.repository.products

import androidx.paging.PagingData
import com.plana.products.core.common.result.RequestState
import com.plana.products.core.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun refreshProducts(): Flow<RequestState<Product>>

    fun refreshProductByID(productID: Int): Flow<RequestState<Product>>

    fun getProducts(): Flow<PagingData<Product>>

    fun getProductByID(productID: Int): Flow<Product>

}