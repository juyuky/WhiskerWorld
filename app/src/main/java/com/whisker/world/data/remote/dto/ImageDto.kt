package com.whisker.world.data.remote.dto

import com.whisker.world.data.local.entity.ImageEntity

data class ImageDto(
    val id: String,
    val url: String,
    val width: String,
    val height: String
) {

    fun toImageEntity(): ImageEntity {
        return ImageEntity(
            id = id,
            url = url
        )
    }
}