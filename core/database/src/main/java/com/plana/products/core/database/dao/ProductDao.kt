package com.plana.products.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.plana.products.core.database.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Upsert
    suspend fun upsertAccounts(vararg productEntity: ProductEntity)

    @Query("SELECT * FROM products WHERE products.id = :productID")
    fun getProductByID(productID: Int): Flow<ProductEntity>

    @Query("SELECT * FROM products WHERE category = :category OR :category IS NULL")
    fun getProductsByCategory(category: String?): PagingSource<Int, ProductEntity>

    @Query("SELECT DISTINCT category FROM products")
    fun getProductsCategories(): Flow<List<String>>

}