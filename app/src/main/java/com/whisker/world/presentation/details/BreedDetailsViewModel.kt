package com.whisker.world.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.usecase.GetBreedByIdUseCase
import com.whisker.world.domain.usecase.UpdateBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
    private val getBreedByIdUseCase: GetBreedByIdUseCase,
    private val updateBreedUseCase: UpdateBreedUseCase
) : ViewModel() {

    companion object {
        private const val CLASS_NAME = "BreedDetailsViewModel"
    }

    private val _state = MutableStateFlow(BreedDetailsState())
    val state get() = _state.asStateFlow()

    fun getBreedById(id: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            id?.let { id ->
                getBreedByIdUseCase.execute(id).onSuccess { breed ->
                    _state.update {
                        it.copy(breed = breed)
                    }
                }
            }
        }
    }

    fun onEvent(event: BreedDetailsEvent) {
        when (event) {
            BreedDetailsEvent.OnFavouriteClicked -> {
                updateFavouriteValue(_state.value.breed)
            }
        }
    }

    private fun updateFavouriteValue(breed: Breed) {
        viewModelScope.launch {
            updateBreedUseCase.execute(breed)
                .onSuccess { breeds ->
                    val updated = breeds.first { it.id == breed.id }
                    _state.update {
                        it.copy(breed = updated)
                    }
                }
                .onFailure { error ->
                    Log.e(CLASS_NAME, "Failed update favourite: $error")
                }
        }
    }
}
