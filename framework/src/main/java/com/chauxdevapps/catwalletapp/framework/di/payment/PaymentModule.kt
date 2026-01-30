package com.chauxdevapps.catwalletapp.framework.di.payment

import com.chauxdevapps.catwalletapp.data.repository.payment.PaymentRepositoryImpl
import com.chauxdevapps.catwalletapp.data.source.payment.PaymentLocalDataSource
import com.chauxdevapps.catwalletapp.data.source.payment.PaymentRemoteDataSource
import com.chauxdevapps.catwalletapp.domain.repository.payment.PaymentRepository
import com.chauxdevapps.catwalletapp.framework.request.payment.MockPaymentRemoteDataSource
import com.chauxdevapps.catwalletapp.framework.storage.payment.EncryptedPaymentLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaymentModule {

    @Binds
    @Singleton
    abstract fun bindPaymentLocalDataSource(
        dataSource: EncryptedPaymentLocalDataSource
    ): PaymentLocalDataSource

    @Binds
    @Singleton
    abstract fun bindPaymentRemoteDataSource(
        dataSource: MockPaymentRemoteDataSource
    ): PaymentRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindPaymentRepository(
        repository: PaymentRepositoryImpl
    ): PaymentRepository
}
