package com.whisker.world.presentation.details

sealed interface BreedDetailsEvent {
    data object onFavouriteClicked : BreedDetailsEvent
}