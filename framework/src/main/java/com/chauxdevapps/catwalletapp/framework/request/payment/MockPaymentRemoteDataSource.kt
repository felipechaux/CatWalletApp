package com.chauxdevapps.catwalletapp.framework.request.payment

import com.chauxdevapps.catwalletapp.data.source.payment.PaymentRemoteDataSource
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.ERROR_INVALID_CARD_NUMBER
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.MIN_CARD_NUMBER_LENGTH
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.MOCK_DELAY_MS
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.TOKEN_LENGTH
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.TOKEN_PREFIX
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

class MockPaymentRemoteDataSource @Inject constructor() : PaymentRemoteDataSource {
    override suspend fun tokenizePaymentMethod(cardNumber: String, cvv: String, expiryDate: String): String {
        // Simulate network delay
        delay(MOCK_DELAY_MS)
        
        // Mock validation
        if (cardNumber.length < MIN_CARD_NUMBER_LENGTH) {
            throw Exception(ERROR_INVALID_CARD_NUMBER)
        }
        
        // Return a mock token
        return TOKEN_PREFIX + UUID.randomUUID().toString().take(TOKEN_LENGTH)
    }
}
