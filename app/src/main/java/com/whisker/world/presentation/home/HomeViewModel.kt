package com.whisker.world.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whisker.world.data.model.Breed
import com.whisker.world.domain.service.BreedService
import com.whisker.world.presentation.BreedUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val breedService: BreedService
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            breedService.getAllBreeds().onSuccess { breeds ->
                _state.update {
                    it.copy(
                        breedUiList = getBreedUi(breeds)
                    )
                }

            }.onFailure { error ->
                Log.e("MainViewModel", "Get breeds failed request error: $error")
            }
        }
    }

    private fun getBreedUi(listBreeds: List<Breed>): List<BreedUi> {
        return listBreeds.map {
            // TODO :: REVERT THIS WHEN REQUEST TO IMAGE IS DONE
            BreedUi(it.name, "https://cdn2.thecatapi.com/images/${it.imageId}.jpg", false)
        }
    }
}