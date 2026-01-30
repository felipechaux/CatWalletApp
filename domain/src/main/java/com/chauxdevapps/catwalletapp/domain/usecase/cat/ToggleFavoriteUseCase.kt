package com.chauxdevapps.catwalletapp.domain.usecase.cat

import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import com.chauxdevapps.catwalletapp.domain.repository.cat.CatRepository
import com.chauxdevapps.catwalletapp.domain.repository.payment.PaymentRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val catRepository: CatRepository,
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(cat: Cat): Result<Unit> {
        val isCurrentlyFavorite = cat.isFavorite
        
        if (!isCurrentlyFavorite) {
            val isUnlimitedAccess = paymentRepository.isUnlimitedAccessEnabled()
            if (!isUnlimitedAccess) {
                val currentFavorites = catRepository.getFavoriteCats()
                if (currentFavorites.size >= 3) {
                    return Result.failure(Exception("LIMIT_REACHED"))
                }
            }
        }
        
        catRepository.toggleFavorite(cat)
        return Result.success(Unit)
    }
}
