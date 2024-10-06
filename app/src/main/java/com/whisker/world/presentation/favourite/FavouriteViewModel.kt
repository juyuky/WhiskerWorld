package com.whisker.world.presentation.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.usecase.GetFavouritesBreedsUseCase
import com.whisker.world.domain.usecase.UpdateBreedUseCase
import com.whisker.world.navigation.Navigation
import com.whisker.world.navigation.NavigationArgs
import com.whisker.world.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavouritesBreedsUseCase: GetFavouritesBreedsUseCase,
    private val updateBreedUseCase: UpdateBreedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavouriteState())
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getFavouritesBreedsUseCase.execute().onSuccess { breeds ->
                _state.update {
                    it.copy(favouritesBreedList = breeds)
                }
            }.onFailure { error ->
                Log.e("FavouriteViewModel", "Get favourite bread failed: $error")
            }
        }
    }

    fun onEvent(event: FavouriteEvent, navController: NavController) {
        when (event) {
            is FavouriteEvent.OnDetailsClicked -> {
                val finalRoute = Routes.DETAILS.replace(
                    NavigationArgs.toPath(NavigationArgs.BREED_ID), event.id
                )
                navController.navigate(finalRoute)
            }

            is FavouriteEvent.OnNavigationBarItemClicked -> {
                navController.navigate(Navigation.Home.destination)
            }

            is FavouriteEvent.OnFavouriteChange -> {
                updateFavouriteValue(event.breed)
            }
        }
    }

    private fun updateFavouriteValue(breed: Breed) {
        viewModelScope.launch {
            updateBreedUseCase.execute(breed).onSuccess { breeds ->
                _state.update {
                    it.copy(favouritesBreedList = breeds)
                }
            }.onFailure {
                // TODO :: LAST ERROR MESSAGE
            }
        }
    }
}
