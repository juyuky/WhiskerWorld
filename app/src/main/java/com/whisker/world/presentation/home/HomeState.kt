package com.whisker.world.presentation.home

import com.whisker.world.domain.model.Breed

data class HomeState(
    val breedUiList: List<Breed> = emptyList()
)