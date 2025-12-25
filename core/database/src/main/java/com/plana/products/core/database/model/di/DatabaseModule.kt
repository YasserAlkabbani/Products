package com.plana.products.core.database.model.di

import android.app.Application
import androidx.room.Room
import com.plana.products.core.database.model.PDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePDatabaseModule(
        @ApplicationContext application: Application
    ) = Room.databaseBuilder(
        application,
        PDatabase::class.java,
        "p-database"
    ).build()

}