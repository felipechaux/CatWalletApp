package com.chauxdevapps.catwalletapp.data.source.payment

interface PaymentLocalDataSource {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
}
