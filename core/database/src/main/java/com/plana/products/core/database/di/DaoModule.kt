package com.plana.products.core.database.di

import com.plana.products.core.database.model.PDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideProductDao(pDatabase: PDatabase) =
        pDatabase.productDao()

}