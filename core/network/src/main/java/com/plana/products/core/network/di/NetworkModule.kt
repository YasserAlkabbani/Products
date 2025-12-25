package com.plana.products.core.network.di

import com.plana.products.core.network.BuildConfig
import com.plana.products.core.network.retrofit.ProductsRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

private const val BASE_URL_PRODUCTS = "https://fakestoreapi.com/"

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }


    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory =
        OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG)
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
            )
            .build()


    @Provides
    @Singleton
    fun provideProductsRetrofit(
        json: Json,
        okHttpClient: dagger.Lazy<Call.Factory>
    ): ProductsRetrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL_PRODUCTS)
            .callFactory { okHttpClient.get().newCall(it) }
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(ProductsRetrofit::class.java)

}