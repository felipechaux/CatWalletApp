package com.chauxdevapps.catwalletapp.domain.usecase.payment

import com.chauxdevapps.catwalletapp.domain.repository.payment.PaymentRepository
import javax.inject.Inject

class TokenizePaymentMethodUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(cardNumber: String, cvv: String, expiryDate: String): Result<String> {
        return try {
            val token = paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate)
            paymentRepository.saveToken(token)
            Result.success(token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
