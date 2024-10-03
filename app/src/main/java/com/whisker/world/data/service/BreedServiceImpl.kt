package com.whisker.world.data.service

import com.whisker.world.data.model.Breed
import com.whisker.world.domain.repository.BreedRepository
import com.whisker.world.domain.service.BreedService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BreedServiceImpl @Inject constructor(
    private val breedRepository: BreedRepository
) : BreedService {

    override suspend fun getAllBreeds(): Result<List<Breed>> =
        withContext(IO) { breedRepository.getAllBreeds() }

}
