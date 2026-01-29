package com.chauxdevapps.catwalletapp.domain.repository

import com.chauxdevapps.catwalletapp.domain.model.Cat

interface CatRepository {
    suspend fun getCats(): List<Cat>
}
