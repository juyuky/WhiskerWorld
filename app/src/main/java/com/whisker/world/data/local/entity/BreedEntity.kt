package com.whisker.world.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.whisker.world.domain.model.Breed

@Entity
data class BreedEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val temperament: String,
    val description: String,
    val origin: String,
    val imageId: String?,
    val imageUrl: String
) {
    fun toBreed(): Breed {
        return Breed(
            id = id,
            name = name,
            temperament = temperament,
            origin = origin,
            description = description,
            imageUrl = imageUrl
        )
    }
}