package com.whisker.world.presentation.home

sealed interface HomeEvent {
    data object OnNavigationBarItemClicked : HomeEvent
    data class OnDetailsClicked(val id: String) : HomeEvent
}