package com.chauxdevapps.catwalletapp.domain.usecase.cat

import com.chauxdevapps.catwalletapp.domain.model.Resource
import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import com.chauxdevapps.catwalletapp.domain.repository.cat.CatRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCatListUseCaseTest {

    private lateinit var catRepository: CatRepository
    private lateinit var getCatListUseCase: GetCatListUseCase

    @Before
    fun setup() {
        catRepository = mockk()
        getCatListUseCase = GetCatListUseCase(catRepository)
    }

    @Test
    fun `invoke should return success with cats when repository succeeds`() = runTest {
        // Given
        val expectedCats = listOf(
            Cat(id = "1", name = "Cat 1", imageUrl = "url1", isFavorite = false),
            Cat(id = "2", name = "Cat 2", imageUrl = "url2", isFavorite = false)
        )
        
        coEvery { catRepository.getCats() } returns expectedCats

        // When
        val result = getCatListUseCase()

        // Then
        assertTrue(result is Resource.Success)
        val cats = (result as Resource.Success).data
        assertEquals(2, cats.size)
        assertEquals(expectedCats, cats)
        
        coVerify { catRepository.getCats() }
    }

    @Test
    fun `invoke should return error when repository throws exception`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { catRepository.getCats() } throws Exception(errorMessage)

        // When
        val result = getCatListUseCase()

        // Then
        assertTrue(result is Resource.Error)
        assertEquals(errorMessage, (result as Resource.Error).message)
        
        coVerify { catRepository.getCats() }
    }

    @Test
    fun `invoke should handle unknown exceptions with default message`() = runTest {
        // Given
        coEvery { catRepository.getCats() } throws Exception()

        // When
        val result = getCatListUseCase()

        // Then
        assertTrue(result is Resource.Error)
        assertEquals("Unknown error", (result as Resource.Error).message)
        
        coVerify { catRepository.getCats() }
    }

    @Test
    fun `invoke should return empty list when repository returns empty list`() = runTest {
        // Given
        coEvery { catRepository.getCats() } returns emptyList()

        // When
        val result = getCatListUseCase()

        // Then
        assertTrue(result is Resource.Success)
        val cats = (result as Resource.Success).data
        assertTrue(cats.isEmpty())
        
        coVerify { catRepository.getCats() }
    }

    @Test
    fun `invoke should preserve cat data correctly`() = runTest {
        // Given
        val cat1 = Cat(id = "1", name = "Fluffy", imageUrl = "url1", isFavorite = true)
        val cat2 = Cat(id = "2", name = "Whiskers", imageUrl = "url2", isFavorite = false)
        val expectedCats = listOf(cat1, cat2)
        
        coEvery { catRepository.getCats() } returns expectedCats

        // When
        val result = getCatListUseCase()

        // Then
        assertTrue(result is Resource.Success)
        val cats = (result as Resource.Success).data
        assertEquals(cat1, cats[0])
        assertEquals(cat2, cats[1])
    }
}
