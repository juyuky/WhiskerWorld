package com.whisker.world.data

import com.whisker.world.data.local.ImageDao
import com.whisker.world.data.local.entity.ImageEntity
import com.whisker.world.data.remote.ImageApi
import com.whisker.world.data.repository.ImageRepositoryImpl
import com.whisker.world.domain.repository.ImageRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ImageRepositoryTest {

    @Mock
    private lateinit var imageApi: ImageApi

    @Mock
    private lateinit var imageDao: ImageDao

    @Mock
    private lateinit var imageRepository: ImageRepository

    @Before
    fun setup() {
        imageRepository = ImageRepositoryImpl(imageApi, imageDao)
    }

    @Test
    fun getAllImages_in_local_storage_success() {
        runTest {
            val imageEntity = listOf(getFakeImageEntity())

            whenever(imageDao.getAll()).thenReturn(imageEntity)

            val result = imageRepository.getAllImages(listOf("id"))
            assert(result.isSuccess)
            assertEquals(imageEntity, result.getOrNull())
        }
    }


    private fun getFakeImageEntity() = ImageEntity(
        id = "id",
        url = "url"
    )
}