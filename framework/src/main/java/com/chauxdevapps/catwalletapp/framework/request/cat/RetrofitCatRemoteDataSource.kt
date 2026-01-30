package com.chauxdevapps.catwalletapp.framework.request.cat

import com.chauxdevapps.catwalletapp.data.source.cat.CatRemoteDataSource
import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import com.chauxdevapps.catwalletapp.domain.model.Resource
import javax.inject.Inject

class RetrofitCatRemoteDataSource @Inject constructor(
    private val catApi: CatApi
) : CatRemoteDataSource {
    override suspend fun getCats(): List<Cat> {
        return catApi.getCats().toDomain()
    }
}
