package com.plana.products.core.database.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plana.products.core.database.dao.ProductDao

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class PDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}