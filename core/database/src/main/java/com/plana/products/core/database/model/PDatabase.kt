package com.plana.products.core.database.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plana.products.core.database.model.dao.ProductDao
import com.plana.products.core.database.model.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class PDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}