package com.whisker.world.presentation.home

sealed interface HomeEvent {
    data object onNavigationBarItemClicked : HomeEvent
    data class OnDetailsClicked(val id: String) : HomeEvent
}