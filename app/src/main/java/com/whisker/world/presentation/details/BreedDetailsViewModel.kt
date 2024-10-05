package com.whisker.world.presentation.details

import androidx.lifecycle.ViewModel
import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.usecase.GetBreedByIdUseCase
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
    private val getBreedByIdUseCase: GetBreedByIdUseCase
) : ViewModel() {

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
            BreedDetailsEvent.onFavouriteClicked -> {
                // TODO :: Missing implementation
            }
        }
    }
}
