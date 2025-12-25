package com.plana.products.core.data.di

import com.plana.products.core.data.repository.products.OfflineFirstProductsRepository
import com.plana.products.core.data.repository.products.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindProductsRepository(
        productsRepository: OfflineFirstProductsRepository
    ): ProductsRepository

}