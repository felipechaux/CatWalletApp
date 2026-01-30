package com.chauxdevapps.catwalletapp.framework.db.cat

import com.chauxdevapps.catwalletapp.data.source.cat.CatLocalDataSource
import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import javax.inject.Inject

class RoomCatLocalDataSource @Inject constructor(
    private val catDao: CatDao
) : CatLocalDataSource {

    override suspend fun getFavoriteCats(): List<Cat> {
        return catDao.getFavoriteCats().map { it.toDomain() }
    }

    override suspend fun toggleFavorite(cat: Cat) {
        val existingCat = catDao.getCatById(cat.id)
        if (existingCat == null) {
            // If not in DB, insert as favorite
            catDao.insertCat(cat.toEntity().copy(isFavorite = true))
        } else {
            // If in DB, toggle favorite status
            catDao.updateFavoriteStatus(cat.id, !existingCat.isFavorite)
        }
    }

    override suspend fun isFavorite(catId: String): Boolean {
        return catDao.getCatById(catId)?.isFavorite ?: false
    }

    override suspend fun saveCat(cat: Cat) {
        // Only save if not already in DB to preserve favorite status
        if (catDao.getCatById(cat.id) == null) {
            catDao.insertCat(cat.toEntity())
        }
    }
}
