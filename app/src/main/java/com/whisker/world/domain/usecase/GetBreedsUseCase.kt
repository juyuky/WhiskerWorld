package com.whisker.world.domain.usecase

import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.repository.BreedRepository
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val breedRepository: BreedRepository
) {

    suspend fun execute(): Result<List<Breed>> {
        return breedRepository.getAllBreeds()
    }

}