package com.whisker.world.presentation.favourite

import com.whisker.world.domain.model.Breed

data class FavouriteState(
    val showError: Boolean = false,
    val favouritesBreedList: List<Breed> = emptyList()
)