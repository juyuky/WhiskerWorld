package com.whisker.world.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.whisker.world.presentation.details.BreedDetailsScreen
import com.whisker.world.presentation.details.BreedDetailsViewModel
import com.whisker.world.presentation.home.HomeScreen
import com.whisker.world.presentation.home.HomeState
import com.whisker.world.presentation.home.HomeViewModel

@Composable
fun NavHost(
    state: HomeState,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Navigation.Home.destination) {
        composable(Navigation.Home.destination) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(state = state) { event ->
                viewModel.onEvent(event, navController)
            }
        }
        composable(Navigation.Details.destination) { backStackEntry ->
            val breedId = backStackEntry.arguments?.getString(NavigationArgs.BREED_ID)
            val viewModel: BreedDetailsViewModel = hiltViewModel()
            BreedDetailsScreen(
                viewModel.getBreedById(breedId),
            ) { event ->
                viewModel.onEvent(event)
            }
        }
        composable(Navigation.Favourites.destination) {
            // TODO :: ADD SCREEN WHEN CREATED
        }
    }

}
