package com.chauxdevapps.catwalletapp.domain.usecase.cat

import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import com.chauxdevapps.catwalletapp.domain.repository.cat.CatRepository
import javax.inject.Inject

class GetFavoriteCatsUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(): List<Cat> {
        return repository.getFavoriteCats()
    }
}
