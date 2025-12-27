package com.plana.products.core.network.di

import com.plana.products.core.network.datasource.products.ProductsNetworkDataSource
import com.plana.products.core.network.datasource.products.ProductsNetworkDataSourceRetrofit
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkDataSourceModule {

    @Binds
    abstract fun bindProductsNetworkDataSource(
        productsNetworkDataSource: ProductsNetworkDataSourceRetrofit
    ): ProductsNetworkDataSource

}