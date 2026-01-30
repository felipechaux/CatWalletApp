package com.chauxdevapps.catwalletapp.domain.repository.payment

interface PaymentRepository {
    suspend fun tokenizePaymentMethod(cardNumber: String, cvv: String, expiryDate: String): String
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun isUnlimitedAccessEnabled(): Boolean
}
