package com.chauxdevapps.catwalletapp.data.repository.payment

import com.chauxdevapps.catwalletapp.data.source.payment.PaymentLocalDataSource
import com.chauxdevapps.catwalletapp.data.source.payment.PaymentRemoteDataSource
import com.chauxdevapps.catwalletapp.domain.repository.payment.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val localDataSource: PaymentLocalDataSource,
    private val remoteDataSource: PaymentRemoteDataSource
) : PaymentRepository {

    override suspend fun tokenizePaymentMethod(cardNumber: String, cvv: String, expiryDate: String): String {
        return remoteDataSource.tokenizePaymentMethod(cardNumber, cvv, expiryDate)
    }

    override suspend fun saveToken(token: String) {
        localDataSource.saveToken(token)
    }

    override suspend fun getToken(): String? {
        return localDataSource.getToken()
    }

    override suspend fun isUnlimitedAccessEnabled(): Boolean {
        return !localDataSource.getToken().isNullOrBlank()
    }
}
