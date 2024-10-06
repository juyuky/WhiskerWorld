package com.whisker.world.domain.usecase

import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.repository.BreedRepository
import com.whisker.world.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val breedRepository: BreedRepository,
    private val imageRepository: ImageRepository
) {

    suspend fun execute(): Result<List<Breed>> =
        withContext(IO) {
            breedRepository.getAllBreeds().onSuccess { breeds ->
                val imageIds: List<String> = breeds.mapNotNull { it.imageId }
                fillImageId(imageIds, breeds)
            }
        }

    private suspend fun fillImageId(imageIds: List<String>, breeds: List<Breed>) {
        val newBreedsList = mutableListOf<Breed>()
        imageRepository.getAllImages(imageIds).onSuccess { imageEntities ->
            imageEntities.forEach { entity ->
                val breed = breeds.first { it.imageId == entity.id }
                breed.imageUrl = entity.url
                newBreedsList.add(breed)
            }
        }
        breedRepository.updateBreeds(breeds)
    }
}
