package com.chauxdevapps.catwalletapp.domain.usecase.cat

import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import com.chauxdevapps.catwalletapp.domain.repository.cat.CatRepository
import com.chauxdevapps.catwalletapp.domain.repository.payment.PaymentRepository
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.ERROR_LIMIT_REACHED
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.FREE_FAVORITE_LIMIT
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ToggleFavoriteUseCaseTest {

    private lateinit var catRepository: CatRepository
    private lateinit var paymentRepository: PaymentRepository
    private lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    @Before
    fun setup() {
        catRepository = mockk(relaxed = true)
        paymentRepository = mockk()
        toggleFavoriteUseCase = ToggleFavoriteUseCase(catRepository, paymentRepository)
    }

    @Test
    fun `invoke should successfully toggle favorite when cat is already favorite`() = runTest {
        // Given
        val cat = Cat(id = "1", name = "Cat 1", imageUrl = "url1", isFavorite = true)

        // When
        val result = toggleFavoriteUseCase(cat)

        // Then
        assertTrue(result.isSuccess)
        coVerify { catRepository.toggleFavorite(cat) }
    }

    @Test
    fun `invoke should successfully add to favorites when under limit and no unlimited access`() = runTest {
        // Given
        val cat = Cat(id = "1", name = "Cat 1", imageUrl = "url1", isFavorite = false)
        val currentFavorites = listOf(
            Cat(id = "2", name = "Cat 2", imageUrl = "url2", isFavorite = true)
        )
        
        coEvery { paymentRepository.isUnlimitedAccessEnabled() } returns false
        coEvery { catRepository.getFavoriteCats() } returns currentFavorites

        // When
        val result = toggleFavoriteUseCase(cat)

        // Then
        assertTrue(result.isSuccess)
        coVerify { catRepository.toggleFavorite(cat) }
        coVerify { paymentRepository.isUnlimitedAccessEnabled() }
        coVerify { catRepository.getFavoriteCats() }
    }

    @Test
    fun `invoke should return error when limit reached and no unlimited access`() = runTest {
        // Given
        val cat = Cat(id = "4", name = "Cat 4", imageUrl = "url4", isFavorite = false)
        val currentFavorites = listOf(
            Cat(id = "1", name = "Cat 1", imageUrl = "url1", isFavorite = true),
            Cat(id = "2", name = "Cat 2", imageUrl = "url2", isFavorite = true),
            Cat(id = "3", name = "Cat 3", imageUrl = "url3", isFavorite = true)
        )
        
        coEvery { paymentRepository.isUnlimitedAccessEnabled() } returns false
        coEvery { catRepository.getFavoriteCats() } returns currentFavorites

        // When
        val result = toggleFavoriteUseCase(cat)

        // Then
        assertTrue(result.isFailure)
        assertEquals(ERROR_LIMIT_REACHED, result.exceptionOrNull()?.message)
        coVerify(exactly = 0) { catRepository.toggleFavorite(any()) }
        coVerify { paymentRepository.isUnlimitedAccessEnabled() }
        coVerify { catRepository.getFavoriteCats() }
    }

    @Test
    fun `invoke should successfully add to favorites when limit reached but has unlimited access`() = runTest {
        // Given
        val cat = Cat(id = "4", name = "Cat 4", imageUrl = "url4", isFavorite = false)
        
        coEvery { paymentRepository.isUnlimitedAccessEnabled() } returns true

        // When
        val result = toggleFavoriteUseCase(cat)

        // Then
        assertTrue(result.isSuccess)
        coVerify { catRepository.toggleFavorite(cat) }
        coVerify { paymentRepository.isUnlimitedAccessEnabled() }
        coVerify(exactly = 0) { catRepository.getFavoriteCats() }
    }

    @Test
    fun `invoke should check limit when exactly at FREE_FAVORITE_LIMIT`() = runTest {
        // Given
        val cat = Cat(id = "4", name = "Cat 4", imageUrl = "url4", isFavorite = false)
        val currentFavorites = List(FREE_FAVORITE_LIMIT) { index ->
            Cat(id = "$index", name = "Cat $index", imageUrl = "url$index", isFavorite = true)
        }
        
        coEvery { paymentRepository.isUnlimitedAccessEnabled() } returns false
        coEvery { catRepository.getFavoriteCats() } returns currentFavorites

        // When
        val result = toggleFavoriteUseCase(cat)

        // Then
        assertTrue(result.isFailure)
        assertEquals(ERROR_LIMIT_REACHED, result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke should allow adding when one below limit`() = runTest {
        // Given
        val cat = Cat(id = "3", name = "Cat 3", imageUrl = "url3", isFavorite = false)
        val currentFavorites = List(FREE_FAVORITE_LIMIT - 1) { index ->
            Cat(id = "$index", name = "Cat $index", imageUrl = "url$index", isFavorite = true)
        }
        
        coEvery { paymentRepository.isUnlimitedAccessEnabled() } returns false
        coEvery { catRepository.getFavoriteCats() } returns currentFavorites

        // When
        val result = toggleFavoriteUseCase(cat)

        // Then
        assertTrue(result.isSuccess)
        coVerify { catRepository.toggleFavorite(cat) }
    }
}
