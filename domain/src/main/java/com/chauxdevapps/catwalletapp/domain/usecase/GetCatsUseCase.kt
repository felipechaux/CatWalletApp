package com.chauxdevapps.catwalletapp.domain.usecase

import com.chauxdevapps.catwalletapp.domain.model.Cat
import com.chauxdevapps.catwalletapp.domain.repository.CatRepository
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(): List<Cat> = repository.getCats()
}
