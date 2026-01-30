package com.chauxdevapps.catwalletapp.data.source.payment

interface PaymentRemoteDataSource {
    suspend fun tokenizePaymentMethod(cardNumber: String, cvv: String, expiryDate: String): String
}
