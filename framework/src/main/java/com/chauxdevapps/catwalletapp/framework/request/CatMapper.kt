package com.chauxdevapps.catwalletapp.framework.request

import com.chauxdevapps.catwalletapp.domain.model.Cat

fun CatDto.toDomain(): Cat {
    return Cat(
        id = id.orEmpty(),
        name = "Cat $id", // The API search doesn't return names usually, just images
        imageUrl = url.orEmpty()
    )
}

fun List<CatDto>.toDomain(): List<Cat> = map { it.toDomain() }
