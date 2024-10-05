package com.whisker.world.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.whisker.world.domain.usecase.GetBreedsUseCase
import com.whisker.world.navigation.NavigationArgs
import com.whisker.world.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase
) : ViewModel() {

    companion object {
        private const val CLASS_NAME = "HomeViewModel"
    }

    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getBreedsUseCase.execute().onSuccess { breeds ->
                _state.update {
                    it.copy(breedUiList = breeds)
                }
            }.onFailure { error ->
                Log.e(CLASS_NAME, "Get breeds failed request error: $error")
            }
        }
    }

    fun onEvent(event: HomeEvent, navController: NavController) {
        when (event) {
            HomeEvent.OnNavigationBarItemClicked -> {
                // TODO :: Missing implementation
            }

            is HomeEvent.OnDetailsClicked -> {
                val finalRoute = Routes.DETAILS.replace(
                    NavigationArgs.toPath(NavigationArgs.BREED_ID), event.id
                )
                navController.navigate(finalRoute)
            }
        }
    }
}