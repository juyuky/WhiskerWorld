package com.whisker.world.domain.model

import com.whisker.world.data.local.entity.BreedEntity

data class Breed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    var imageUrl: String? = null,
    val imageId: String?,
    var isFavourite: Boolean = false
) {
    fun toBreedEntity(): BreedEntity {
        return BreedEntity(
            id = id,
            name = name,
            temperament = temperament,
            description = description,
            origin = origin,
            imageUrl = imageUrl,
            imageId = imageId,
            isFavourite = isFavourite
        )
    }
}
