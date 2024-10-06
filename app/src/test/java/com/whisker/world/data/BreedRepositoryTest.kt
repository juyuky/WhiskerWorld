package com.whisker.world.data

import com.whisker.world.data.local.BreedDao
import com.whisker.world.data.local.entity.BreedEntity
import com.whisker.world.data.remote.BreedApi
import com.whisker.world.data.remote.dto.BreedDto
import com.whisker.world.data.repository.BreedRepositoryImpl
import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.repository.BreedRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


@RunWith(MockitoJUnitRunner::class)
class BreedRepositoryTest {

    @Mock
    private lateinit var breedApi: BreedApi

    @Mock
    private lateinit var breedDao: BreedDao

    @Mock
    private lateinit var breedRepository: BreedRepository

    @Before
    fun setUp() {
        breedRepository = BreedRepositoryImpl(breedApi, breedDao)
    }

    @Test
    fun getAllBreeds_available_in_local_storage_success() {
        runTest {
            val breedEntities = listOf(getFakeBreedEntity())
            val breeds = breedEntities.map { it.toBreed() }

            whenever(breedDao.getAll()).thenReturn(breedEntities)

            val result = breedRepository.getAllBreeds()
            assertTrue(result.isSuccess)
            assertEquals(breeds, result.getOrNull())
        }
    }

    @Test
    fun getAllBreeds_remote_success() {
        runTest {
            val breedsDto = listOf(getFakeBreedDto())
            val response = Response.success(breedsDto)
            val breeds = breedsDto.map { it.toBreed() }
            val mockCall: Call<List<BreedDto>> = mock()

            whenever(breedDao.getAll()).thenReturn(emptyList())
            whenever(breedApi.getAll()).thenReturn(mockCall)
            whenever(breedApi.getAll().execute()).thenReturn(response)

            val result = breedRepository.getAllBreeds()

            assertTrue(result.isSuccess)
            assertEquals(breeds, result.getOrNull())
            verify(breedDao).getAll()
        }
    }

    @Test
    fun getAllBreeds_remote_not_success() {
        runTest {
            val mockCall: Call<List<BreedDto>> = mock()

            whenever(breedDao.getAll()).thenReturn(emptyList())
            whenever(breedApi.getAll()).thenReturn(mockCall)
            whenever(breedApi.getAll().execute()).thenThrow(IOException::class.java)

            val result = breedRepository.getAllBreeds()

            assertTrue(result.isFailure)
        }
    }

    @Test
    fun getBreedById_success() {
        runTest {
            val id = "id"
            val breedEntity = getFakeBreedEntity()
            val breed = breedEntity.toBreed()

            whenever(breedDao.getById(id)).thenReturn(breedEntity)

            val result = breedRepository.getBreedById(id)
            assertTrue(result.isSuccess)
            assertEquals(breed, result.getOrNull())
        }
    }

    @Test
    fun getBreedById_error() {
        runTest {
            val id = "otherId"

            whenever(breedDao.getById(id)).thenReturn(null)

            val result = breedRepository.getBreedById(id)
            assertTrue(result.isFailure)
        }
    }

    @Test
    fun getBreedsByName_success() {
        runTest {
            val name = "name"
            val breedEntities = listOf(getFakeBreedEntity())
            val breeds = breedEntities.map { it.toBreed() }

            whenever(breedDao.getByName(name)).thenReturn(breedEntities)

            val result = breedRepository.getBreedsByName(name)
            assertTrue(result.isSuccess)
            assertEquals(breeds, result.getOrNull())
        }
    }

    @Test
    fun getBreedsByName_empty() {
        runTest {
            val name = "otherName"

            whenever(breedDao.getByName(name)).thenReturn(emptyList())

            val result = breedRepository.getBreedsByName(name)
            assertTrue(result.isSuccess)
            assertEquals(emptyList<Breed>(), result.getOrNull())
        }
    }

    @Test
    fun getFavouriteBreeds_success() {
        runTest {
            val isFavourites = true
            val breedEntities = listOf(getFakeBreedEntity())
            val breeds = breedEntities.map { it.toBreed() }

            whenever(breedDao.getByFavourite(isFavourites)).thenReturn(breedEntities)

            val result = breedRepository.getFavouriteBreeds()
            assertTrue(result.isSuccess)
            assertEquals(breeds, result.getOrNull())
        }
    }

    @Test
    fun getFavouriteBreed_error() {
        runTest {
            whenever(breedDao.getByFavourite(true)).thenReturn(emptyList())

            val result = breedRepository.getFavouriteBreeds()
            assertTrue(result.isFailure)
        }
    }

    @Test
    fun updateBreeds_success() {
        runTest {
            val breedEntities = listOf(getFakeBreedEntity())
            val breeds = breedEntities.map { it.toBreed() }

            whenever(breedDao.getAll()).thenReturn(breedEntities)

            val result = breedRepository.updateBreeds(breeds)
            assertTrue(result.isSuccess)
        }
    }

    @Test
    fun updateBreeds_error() {
        runTest {
            whenever(breedDao.getAll()).thenReturn(emptyList())

            val result = breedRepository.updateBreeds(emptyList())
            assertTrue(result.isFailure)
        }
    }

    private fun getFakeBreedEntity() = BreedEntity(
        id = "id",
        name = "name",
        temperament = "temperament",
        description = "description",
        origin = "origin",
        imageId = "imageId",
        isFavourite = true
    )

    private fun getFakeBreedDto() = BreedDto(
        id = "id",
        name = "name",
        temperament = "temperament",
        description = "description",
        origin = "origin",
        imageId = "imageId"
    )
}