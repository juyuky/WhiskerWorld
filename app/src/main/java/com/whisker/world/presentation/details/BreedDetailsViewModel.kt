package com.whisker.world.presentation.details

import androidx.lifecycle.ViewModel
import com.whisker.world.presentation.BreedUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
) : ViewModel() {

    fun getBreedById(id: String?): BreedUi {
        // TODO REVERT THIS WHEN ROOM IS IMPLEMENTED
        return BreedUi(
            id = "123",
            name = "Siamese",
            description = "While Siamese cats are extremely fond of their people, they will follow you around and supervise your every move, being talkative and opinionated. They are a demanding and social cat, that do not like being left alone for long periods",
            imageUrl = "https://example.com/image.jpg",
            origin = "Thailand",
            temperament = "Active, Agile, Clever, Sociable, Loving, Energetic",
            isFavorite = false

        )
    }

    fun onEvent(event: BreedDetailsEvent) {
        when (event) {
            BreedDetailsEvent.onFavouriteClicked -> {
                // TODO :: Missing implementation
            }
        }
    }
}
