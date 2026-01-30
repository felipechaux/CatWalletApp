package com.chauxdevapps.catwalletapp.framework.di

import android.content.Context
import androidx.room.Room
import com.chauxdevapps.catwalletapp.data.source.cat.CatLocalDataSource
import com.chauxdevapps.catwalletapp.framework.db.cat.CatDao
import com.chauxdevapps.catwalletapp.framework.db.cat.CatDatabase
import com.chauxdevapps.catwalletapp.framework.db.cat.RoomCatLocalDataSource
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
    fun provideDatabase(@ApplicationContext context: Context): CatDatabase {
        return Room.databaseBuilder(
            context,
            CatDatabase::class.java,
            "cat_database"
        ).build()
    }

    @Provides
    fun provideCatDao(database: CatDatabase): CatDao {
        return database.catDao()
    }

    @Provides
    @Singleton
    fun provideCatLocalDataSource(catDao: CatDao): CatLocalDataSource {
        return RoomCatLocalDataSource(catDao)
    }
}
