package com.whisker.world.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.whisker.world.presentation.details.BreedDetailsScreen
import com.whisker.world.presentation.details.BreedDetailsViewModel
import com.whisker.world.presentation.favourite.FavouriteScreen
import com.whisker.world.presentation.favourite.FavouriteViewModel
import com.whisker.world.presentation.home.HomeScreen
import com.whisker.world.presentation.home.HomeViewModel

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavHost(navController = navController, startDestination = Navigation.Home.destination) {
        composable(Navigation.Home.destination) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                currentRoute = currentRoute,
                state = viewModel.state.collectAsState().value,
                onEvent = { event ->
                    viewModel.onEvent(event, navController)
                }
            )
        }
        composable(Navigation.Details.destination) { backStackEntry ->
            val viewModel: BreedDetailsViewModel = hiltViewModel()
            val breedId = backStackEntry.arguments?.getString(NavigationArgs.BREED_ID)
            // TODO: LAST: REVIEW THIS
            viewModel.getBreedById(breedId)
            BreedDetailsScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = { event ->
                    viewModel.onEvent(event)
                }
            )
        }
        composable(Navigation.Favourites.destination) {
            val viewModel: FavouriteViewModel = hiltViewModel()
            FavouriteScreen(
                currentRoute = currentRoute,
                state = viewModel.state.collectAsState().value,
                onEvent = { event ->
                    viewModel.onEvent(event, navController)
                }
            )
        }
    }

}
