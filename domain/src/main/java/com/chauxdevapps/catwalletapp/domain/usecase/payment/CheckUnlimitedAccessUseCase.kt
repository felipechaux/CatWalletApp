package com.chauxdevapps.catwalletapp.domain.usecase.payment

import com.chauxdevapps.catwalletapp.domain.repository.payment.PaymentRepository
import javax.inject.Inject

class CheckUnlimitedAccessUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(): Boolean {
        return paymentRepository.isUnlimitedAccessEnabled()
    }
}
