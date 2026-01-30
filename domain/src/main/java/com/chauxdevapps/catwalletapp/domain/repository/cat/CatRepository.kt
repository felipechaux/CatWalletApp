package com.chauxdevapps.catwalletapp.domain.repository.cat

import com.chauxdevapps.catwalletapp.domain.model.cat.Cat

interface CatRepository {
    suspend fun getCats(): List<Cat>
    suspend fun toggleFavorite(cat: Cat)
    suspend fun getFavoriteCats(): List<Cat>
}
