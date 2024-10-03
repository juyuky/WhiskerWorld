package com.whisker.world.presentation.home

import com.whisker.world.presentation.BreedUi

data class HomeState(
    val breedUiList: List<BreedUi> = emptyList()
)