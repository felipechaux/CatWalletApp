package com.chauxdevapps.catwalletapp.domain.model.cat

data class Cat(
    val id: String,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)
