package com.whisker.world.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.whisker.world.data.local.entity.BreedEntity
import com.whisker.world.domain.model.Breed

data class BreedDto(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    @SerializedName("reference_image_id")
    val imageId: String
) {
    fun toBreedEntity(): BreedEntity {
        return BreedEntity(
            id = id,
            name = name,
            temperament = temperament,
            description = description,
            origin = origin,
            imageId = imageId
        )
    }

    fun toBreed(): Breed {
        return Breed(
            id = id,
            name = name,
            temperament = temperament,
            description = description,
            origin = origin,
            imageUrl = "",
            imageId = imageId
        )
    }
}
