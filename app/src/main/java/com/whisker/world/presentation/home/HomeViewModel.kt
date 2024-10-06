package com.whisker.world.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.usecase.GetBreedsByNameUseCase
import com.whisker.world.domain.usecase.GetBreedsUseCase
import com.whisker.world.domain.usecase.UpdateBreedUseCase
import com.whisker.world.navigation.Navigation
import com.whisker.world.navigation.NavigationArgs
import com.whisker.world.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val getBreedsByNameUseCase: GetBreedsByNameUseCase,
    private val updateBreedUseCase: UpdateBreedUseCase
) : ViewModel() {

    companion object {
        private const val CLASS_NAME = "HomeViewModel"
    }

    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    init {
        updateLoadingState(true)
        viewModelScope.launch {
            getBreedsUseCase.execute().onSuccess { breeds ->
                _state.update {
                    it.copy(
                        breedUiList = breeds,
                        isLoading = false
                    )
                }
            }.onFailure { error ->
                Log.e(CLASS_NAME, "Get breeds failed request error: $error")
                updateLoadingState(false)
                updateErrorState(true)
            }
        }
    }

    private fun onSearch(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            getBreedsByNameUseCase.execute(query).onSuccess { breeds ->
                _state.update {
                    it.copy(breedUiList = breeds)
                }
            }
        }
    }

    fun onEvent(event: HomeEvent, navController: NavController) {
        when (event) {
            is HomeEvent.OnNavigationBarItemClicked -> {
                navController.navigate(Navigation.Favourites.destination)
            }

            is HomeEvent.OnDetailsClicked -> {
                val finalRoute = Routes.DETAILS.replace(
                    NavigationArgs.toPath(NavigationArgs.BREED_ID), event.id
                )
                navController.navigate(finalRoute)
            }

            is HomeEvent.OnSearch -> {
                onSearch(event.query)
            }

            is HomeEvent.OnFavouriteChange -> {
                updateFavouriteValue(event.breed)
            }
        }
    }

    private fun updateFavouriteValue(breed: Breed) {
        viewModelScope.launch {
            updateBreedUseCase.execute(breed)
                .onSuccess { breeds ->
                    _state.update {
                        it.copy(breedUiList = breeds)
                    }
                }
                .onFailure { error ->
                    Log.e(CLASS_NAME, "Failed update favourite: $error")
                }
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }

    private fun updateErrorState(showError: Boolean) {
        _state.update {
            it.copy(showError = showError)
        }
    }
}