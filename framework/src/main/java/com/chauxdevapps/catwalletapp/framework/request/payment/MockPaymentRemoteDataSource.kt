package com.chauxdevapps.catwalletapp.framework.request.payment

import com.chauxdevapps.catwalletapp.data.source.payment.PaymentRemoteDataSource
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

class MockPaymentRemoteDataSource @Inject constructor() : PaymentRemoteDataSource {
    override suspend fun tokenizePaymentMethod(cardNumber: String, cvv: String, expiryDate: String): String {
        // Simulate network delay
        delay(1500)
        
        // Mock validation
        if (cardNumber.length < 13) {
            throw Exception("Invalid Card Number")
        }
        
        // Return a mock token
        return "tok_" + UUID.randomUUID().toString().take(12)
    }
}
