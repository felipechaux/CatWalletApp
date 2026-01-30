package com.chauxdevapps.catwalletapp.framework.db.cat

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chauxdevapps.catwalletapp.domain.model.cat.Cat

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean
)

fun CatEntity.toDomain(): Cat = Cat(
    id = id,
    name = name,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)

fun Cat.toEntity(): CatEntity = CatEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    isFavorite = isFavorite
)
