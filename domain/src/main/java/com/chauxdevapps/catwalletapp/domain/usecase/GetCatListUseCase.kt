package com.chauxdevapps.catwalletapp.domain.usecase

import com.chauxdevapps.catwalletapp.domain.model.Cat
import com.chauxdevapps.catwalletapp.domain.model.Resource
import com.chauxdevapps.catwalletapp.domain.repository.CatRepository
import javax.inject.Inject

class GetCatListUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(): Resource<List<Cat>> = try {
        Resource.Success(repository.getCats())
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Unknown error", e)
    }
}
