package com.whisker.world.navigation

private const val HOME = "home"
private const val FAVOURITES = "favourites"
private const val DETAILS = "details"

sealed class Navigation(val destination: String) {
    object Home : Navigation(HOME)
    object Favourites : Navigation(FAVOURITES)
    object Details : Navigation(DETAILS)
}

sealed class Route(val value: String)