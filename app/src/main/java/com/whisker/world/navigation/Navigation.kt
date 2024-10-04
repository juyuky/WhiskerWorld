package com.whisker.world.navigation

object NavigationArgs {
    const val BREED_ID = "BREED_ID"

    fun toPath(param: String) = "{${param}}"
}

object Routes {
    const val HOME = "home"
    const val FAVOURITES = "favourites"
    const val DETAILS = "details/{${NavigationArgs.BREED_ID}}"
}

sealed class Navigation(val destination: String) {
    object Home : Navigation(Routes.HOME)
    object Favourites : Navigation(Routes.FAVOURITES)
    object Details : Navigation(Routes.DETAILS)
}
