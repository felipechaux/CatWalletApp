package com.chauxdevapps.catwalletapp.data.repository.cat

import com.chauxdevapps.catwalletapp.data.source.cat.CatLocalDataSource
import com.chauxdevapps.catwalletapp.data.source.cat.CatRemoteDataSource
import com.chauxdevapps.catwalletapp.data.source.payment.PaymentLocalDataSource
import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import com.chauxdevapps.catwalletapp.domain.model.Resource
import com.chauxdevapps.catwalletapp.domain.repository.cat.CatRepository
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val remoteDataSource: CatRemoteDataSource,
    private val localDataSource: CatLocalDataSource
) : CatRepository {
    override suspend fun getCats(): List<Cat> {
        val remoteCats = remoteDataSource.getCats()
        return remoteCats.map { cat ->
            cat.copy(isFavorite = localDataSource.isFavorite(cat.id))
        }
    }

    override suspend fun toggleFavorite(cat: Cat) {
        localDataSource.toggleFavorite(cat)
    }

    override suspend fun getFavoriteCats(): List<Cat> {
        return localDataSource.getFavoriteCats()
    }
}
