package com.whisker.world.domain.repository

import com.whisker.world.data.local.entity.ImageEntity

interface ImageRepository {

    suspend fun getAllImages(imagesIds: List<String>): Result<List<ImageEntity>>

}