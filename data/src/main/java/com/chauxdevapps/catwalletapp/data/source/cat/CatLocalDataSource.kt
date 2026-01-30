package com.chauxdevapps.catwalletapp.data.source.cat

import com.chauxdevapps.catwalletapp.domain.model.cat.Cat

interface CatLocalDataSource {
    suspend fun getFavoriteCats(): List<Cat>
    suspend fun toggleFavorite(cat: Cat)
    suspend fun isFavorite(catId: String): Boolean
    suspend fun saveCat(cat: Cat)
}
