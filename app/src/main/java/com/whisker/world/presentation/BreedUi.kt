package com.whisker.world.presentation

data class BreedUi(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val origin: String,
    val temperament: String,
    var isFavorite: Boolean
)