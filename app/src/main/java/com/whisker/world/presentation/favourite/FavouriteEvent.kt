package com.whisker.world.presentation.favourite

import com.whisker.world.domain.model.Breed

sealed interface FavouriteEvent {
    data object OnNavigationBarItemClicked : FavouriteEvent
    data class OnDetailsClicked(val id: String) : FavouriteEvent
    data class OnFavouriteChange(val breed: Breed) : FavouriteEvent
}
