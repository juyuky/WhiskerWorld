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

    private fun getFakeBreedEntity() = BreedEntity(
        id = "id",
        name = "name",
        temperament = "temperament",
        description = "description",
        origin = "origin",
        imageId = "imageId",
        imageUrl = "imageUrl"
    )
}