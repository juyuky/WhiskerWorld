package com.whisker.world.domain.usecase

import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.repository.BreedRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBreedsByNameUseCase @Inject constructor(
    private val breedRepository: BreedRepository
) {

    suspend fun execute(name: String): Result<List<Breed>> =
        withContext(IO) {
            breedRepository.getBreedsByName(name)
        }
}
