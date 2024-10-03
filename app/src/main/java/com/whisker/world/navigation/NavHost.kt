package com.whisker.world.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.whisker.world.presentation.home.HomeScreen
import com.whisker.world.presentation.home.HomeState

@Composable
fun NavHost(
    state: HomeState,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Navigation.Home.destination) {
        composable(Navigation.Home.destination) {
            HomeScreen(state) { }
        }
        composable(Navigation.Details.destination) {
            // TODO :: ADD SCREEN WHEN CREATED
        }
        composable(Navigation.Favourites.destination) {
            // TODO :: ADD SCREEN WHEN CREATED
        }
    }

}
