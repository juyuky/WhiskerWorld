package com.whisker.world.domain.usecase

import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.repository.BreedRepository
import javax.inject.Inject


class GetBreedByIdUseCase @Inject constructor(
    private val breedRepository: BreedRepository
) {

    suspend fun execute(id: String): Result<Breed> {
        return breedRepository.getBreedById(id)
    }
}