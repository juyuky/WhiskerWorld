package com.whisker.world.domain.model

data class Breed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    var imageUrl: String? = null,
    val imageId: String?
)
