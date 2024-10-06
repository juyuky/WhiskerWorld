package com.whisker.world.presentation.home

import com.whisker.world.domain.model.Breed

sealed interface HomeEvent {
    data object OnNavigationBarItemClicked : HomeEvent
    data class OnDetailsClicked(val id: String) : HomeEvent
    data class OnSearch(val query: String) : HomeEvent
    data class OnFavouriteChange(val breed: Breed) : HomeEvent
}
