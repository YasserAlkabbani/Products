package com.plana.products.core.data.repository.products

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.plana.products.core.common.result.RequestState
import com.plana.products.core.common.result.requestWithState
import com.plana.products.core.data.model.asEntity
import com.plana.products.core.data.model.asModel
import com.plana.products.core.database.model.dao.ProductDao
import com.plana.products.core.model.Product
import com.plana.products.core.network.datasource.products.ProductsNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstProductsRepository @Inject constructor(
    private val productsNetworkDataSource: ProductsNetworkDataSource,
    private val productDao: ProductDao
) : ProductsRepository {

    override fun refreshProducts(): Flow<RequestState<Product>> = requestWithState {
        val productsEntity = productsNetworkDataSource
            .getProducts()
            .mapNotNull { it.asEntity() }
            .toTypedArray()
        productDao.upsertAccounts(*productsEntity)
        productsEntity.map { it.asModel() }
    }

    override fun refreshProductByID(productID: Int): Flow<RequestState<Product>> =
        requestWithState {
            val productEntity = productsNetworkDataSource
                .getProductByID(productID)
                .asEntity()!!
            productDao.upsertAccounts(productEntity)
            productEntity?.asModel()
        }

    override fun getProducts(): Flow<PagingData<Product>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { productDao.getProducts() }
        )
            .flow.map { it.map { it.asModel() } }
            .flowOn(Dispatchers.Default)

    override fun getProductByID(productID: Int): Flow<Product> =
        productDao
            .getProductByID(productID)
            .map { it.asModel() }
            .flowOn(Dispatchers.Default)


}