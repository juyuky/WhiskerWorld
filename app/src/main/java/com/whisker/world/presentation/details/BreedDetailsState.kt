package com.whisker.world.presentation.details

import com.whisker.world.domain.model.Breed

data class BreedDetailsState(
    val breed: Breed = Breed(
        id = "",
        name = "",
        temperament = "",
        origin = "",
        description = "",
        imageUrl = "",
        imageId = ""
    )
)