package com.chauxdevapps.catwalletapp.data.source

import com.chauxdevapps.catwalletapp.domain.model.Cat

interface CatRemoteDataSource {
    suspend fun getCats(): List<Cat>
}
