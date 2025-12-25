package com.plana.products.core.database.model.dao

import androidx.paging.PagingSource
import androidx.room.Query
import androidx.room.Upsert
import com.plana.products.core.database.model.model.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductDao {

    @Upsert
    suspend fun upsertAccounts(vararg productEntity: ProductEntity)

    @Query("SELECT * FROM products WHERE products.id = :productID")
    fun getProductByID(productID:Int): Flow<ProductEntity>

    @Query("SELECT * FROM products")
    fun getProducts(): PagingSource<Int, ProductEntity>

}