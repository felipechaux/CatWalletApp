package com.chauxdevapps.catwalletapp.data.di

import com.chauxdevapps.catwalletapp.data.repository.CatRepositoryImpl
import com.chauxdevapps.catwalletapp.domain.repository.CatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindCatRepository(catRepositoryImpl: CatRepositoryImpl): CatRepository
}
