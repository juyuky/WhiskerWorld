package com.whisker.world.presentation.home

import com.whisker.world.domain.model.Breed

data class HomeState(
    val showError: Boolean = false,
    val breedUiList: List<Breed> = emptyList()
)
