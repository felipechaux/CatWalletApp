package com.chauxdevapps.catwalletapp.domain.usecase.payment

import com.chauxdevapps.catwalletapp.domain.repository.payment.PaymentRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TokenizePaymentMethodUseCaseTest {

    private lateinit var paymentRepository: PaymentRepository
    private lateinit var tokenizePaymentMethodUseCase: TokenizePaymentMethodUseCase

    @Before
    fun setup() {
        paymentRepository = mockk()
        tokenizePaymentMethodUseCase = TokenizePaymentMethodUseCase(paymentRepository)
    }

    @Test
    fun `invoke should return success with token when tokenization succeeds`() = runTest {
        // Given
        val cardNumber = "4111111111111111"
        val cvv = "123"
        val expiryDate = "1225"
        val expectedToken = "tok_abc123xyz"
        
        coEvery { 
            paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) 
        } returns expectedToken
        coEvery { paymentRepository.saveToken(expectedToken) } just runs

        // When
        val result = tokenizePaymentMethodUseCase(cardNumber, cvv, expiryDate)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedToken, result.getOrNull())
        coVerify { paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) }
        coVerify { paymentRepository.saveToken(expectedToken) }
    }

    @Test
    fun `invoke should return failure when tokenization fails`() = runTest {
        // Given
        val cardNumber = "4111111111111111"
        val cvv = "123"
        val expiryDate = "1225"
        val errorMessage = "Invalid card number"
        
        coEvery { 
            paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) 
        } throws Exception(errorMessage)

        // When
        val result = tokenizePaymentMethodUseCase(cardNumber, cvv, expiryDate)

        // Then
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
        coVerify { paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) }
        coVerify(exactly = 0) { paymentRepository.saveToken(any()) }
    }

    @Test
    fun `invoke should return failure when saveToken fails`() = runTest {
        // Given
        val cardNumber = "4111111111111111"
        val cvv = "123"
        val expiryDate = "1225"
        val token = "tok_abc123"
        val error = Exception("Save failed")
        
        coEvery { 
            paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) 
        } returns token
        coEvery { paymentRepository.saveToken(token) } throws error

        // When
        val result = tokenizePaymentMethodUseCase(cardNumber, cvv, expiryDate)

        // Then
        assertTrue(result.isFailure)
        assertEquals("Save failed", result.exceptionOrNull()?.message)
        coVerify { paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) }
        coVerify { paymentRepository.saveToken(token) }
    }

    @Test
    fun `invoke should handle network timeout exception`() = runTest {
        // Given
        val cardNumber = "4111111111111111"
        val cvv = "123"
        val expiryDate = "1225"
        
        coEvery { 
            paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) 
        } throws Exception("Network timeout")

        // When
        val result = tokenizePaymentMethodUseCase(cardNumber, cvv, expiryDate)

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message?.contains("timeout") == true)
    }

    @Test
    fun `invoke should pass through all parameters correctly`() = runTest {
        // Given
        val cardNumber = "5555555555554444"
        val cvv = "456"
        val expiryDate = "0627"
        val expectedToken = "tok_xyz789abc"
        
        coEvery { 
            paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) 
        } returns expectedToken
        coEvery { paymentRepository.saveToken(expectedToken) } just runs

        // When
        val result = tokenizePaymentMethodUseCase(cardNumber, cvv, expiryDate)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedToken, result.getOrNull())
        coVerify(exactly = 1) { 
            paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) 
        }
        coVerify(exactly = 1) { paymentRepository.saveToken(expectedToken) }
    }

    @Test
    fun `invoke should handle multiple tokenization attempts`() = runTest {
        // Given
        val cardNumber = "4111111111111111"
        val cvv = "123"
        val expiryDate = "1225"
        val token1 = "tok_first"
        val token2 = "tok_second"
        
        coEvery { 
            paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) 
        } returnsMany listOf(token1, token2)
        coEvery { paymentRepository.saveToken(any()) } just runs

        // When
        val result1 = tokenizePaymentMethodUseCase(cardNumber, cvv, expiryDate)
        val result2 = tokenizePaymentMethodUseCase(cardNumber, cvv, expiryDate)

        // Then
        assertTrue(result1.isSuccess)
        assertTrue(result2.isSuccess)
        assertEquals(token1, result1.getOrNull())
        assertEquals(token2, result2.getOrNull())
        coVerify(exactly = 2) { 
            paymentRepository.tokenizePaymentMethod(cardNumber, cvv, expiryDate) 
        }
        coVerify { paymentRepository.saveToken(token1) }
        coVerify { paymentRepository.saveToken(token2) }
    }
}
