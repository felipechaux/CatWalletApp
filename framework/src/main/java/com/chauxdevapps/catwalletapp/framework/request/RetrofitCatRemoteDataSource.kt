package com.chauxdevapps.catwalletapp.framework.request

import com.chauxdevapps.catwalletapp.data.source.CatRemoteDataSource
import com.chauxdevapps.catwalletapp.domain.model.Cat
import javax.inject.Inject

class RetrofitCatRemoteDataSource @Inject constructor(
    private val catApi: CatApi
) : CatRemoteDataSource {
    override suspend fun getCats(): List<Cat> {
        return catApi.getCats()
    }
}
