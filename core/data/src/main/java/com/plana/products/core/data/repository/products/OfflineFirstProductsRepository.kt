package com.plana.products.core.data.repository.products

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.plana.products.core.common.request.RequestState
import com.plana.products.core.common.request.requestWithState
import com.plana.products.core.data.model.asEntity
import com.plana.products.core.data.model.asModel
import com.plana.products.core.database.dao.ProductDao
import com.plana.products.core.model.Product
import com.plana.products.core.network.datasource.products.ProductsNetworkDataSourceRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstProductsRepository @Inject constructor(
    private val productsNetworkDataSource: ProductsNetworkDataSourceRetrofit,
    private val productDao: ProductDao
) : ProductsRepository {

    override fun refreshProducts(): Flow<RequestState<Unit>> = requestWithState {
        val productsEntity = productsNetworkDataSource
            .getProducts()
            .mapNotNull { it.asEntity() }
            .toTypedArray()
        productDao.upsertAccounts(*productsEntity)
    }

    override fun refreshProductByID(productID: Int): Flow<RequestState<Unit>> =
        requestWithState {
            val productEntity = productsNetworkDataSource
                .getProductByID(productID)
                .asEntity()!!
            productDao.upsertAccounts(productEntity)
        }

    override fun getProductsCategories(): Flow<List<String>> =
        productDao.getProductsCategories()

    override fun getProductsByCategory(category: String?): Flow<PagingData<Product>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { productDao.getProductsByCategory(category) }
        )
            .flow.map { it.map { it.asModel() } }
            .flowOn(Dispatchers.Default)

    override fun getProductByID(productID: Int): Flow<Product> =
        productDao
            .getProductByID(productID)
            .map { it.asModel() }
            .flowOn(Dispatchers.Default)

}