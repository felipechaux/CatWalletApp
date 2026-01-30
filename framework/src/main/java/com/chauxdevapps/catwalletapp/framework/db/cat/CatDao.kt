package com.chauxdevapps.catwalletapp.framework.db.cat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    suspend fun getAllCats(): List<CatEntity>

    @Query("SELECT * FROM cats WHERE isFavorite = 1")
    suspend fun getFavoriteCats(): List<CatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(cat: CatEntity)

    @Query("UPDATE cats SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean)

    @Query("SELECT * FROM cats WHERE id = :id")
    suspend fun getCatById(id: String): CatEntity?
}
