package com.whisker.world.data

import com.whisker.world.data.local.BreedDao
import com.whisker.world.data.local.entity.BreedEntity
import com.whisker.world.data.remote.BreedApi
import com.whisker.world.data.repository.BreedRepositoryImpl
import com.whisker.world.domain.repository.BreedRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

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
        runBlocking {
            val breedEntities = listOf(getFakeBreedEntity())
            val breeds = breedEntities.map { it.toBreed() }

            whenever(breedDao.getAll()).thenReturn(breedEntities)

            val result = breedRepository.getAllBreeds()
            assertTrue(result.isSuccess)
            assertEquals(breeds, result.getOrNull())
        }
    }

    @Test
    fun getBreedById_success() {
        runBlocking {
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
        runBlocking {
            val id = "otherId"

            whenever(breedDao.getById(id)).thenReturn(null)

            val result = breedRepository.getBreedById(id)
            assertTrue(result.isFailure)
        }
    }

    @Test
    fun getBreedsByName_success() {
        runBlocking {
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
    fun getBreedsByName_error() {
        runBlocking {
            val name = "otherName"

            whenever(breedDao.getByName(name)).thenReturn(emptyList())

            val result = breedRepository.getBreedsByName(name)
            assertTrue(result.isFailure)
        }
    }

    @Test
    fun getFavouriteBreeds_success() {
        runBlocking {
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
        runBlocking {
            whenever(breedDao.getByFavourite(true)).thenReturn(emptyList())

            val result = breedRepository.getFavouriteBreeds()
            assertTrue(result.isFailure)
        }
    }

    @Test
    fun updateBreeds_success() {
        runBlocking {
            val breedEntities = listOf(getFakeBreedEntity())
            val breeds = breedEntities.map { it.toBreed() }

            whenever(breedDao.getAll()).thenReturn(breedEntities)

            val result = breedRepository.updateBreeds(breeds)
            assertTrue(result.isSuccess)
        }
    }

    @Test
    fun updateBreeds_error() {
        runBlocking {
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
}