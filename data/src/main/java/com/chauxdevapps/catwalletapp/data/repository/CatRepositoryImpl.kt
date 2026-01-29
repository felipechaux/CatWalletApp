package com.chauxdevapps.catwalletapp.data.repository

import com.chauxdevapps.catwalletapp.data.source.CatRemoteDataSource
import com.chauxdevapps.catwalletapp.domain.model.Cat
import com.chauxdevapps.catwalletapp.domain.repository.CatRepository
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val remoteDataSource: CatRemoteDataSource
) : CatRepository {
    override suspend fun getCats(): List<Cat> {
        return remoteDataSource.getCats()
    }
}
