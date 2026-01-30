package com.chauxdevapps.catwalletapp.data.source.cat

import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import com.chauxdevapps.catwalletapp.domain.model.Resource

interface CatRemoteDataSource {
    suspend fun getCats(): List<Cat>
}
