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
        imageRepository.getAllImages(imageIds).onSuccess { imageEntities ->
            imageEntities.forEach { entity ->
                breeds.first { it.imageId == entity.id }.imageUrl = entity.url
            }
        }
    }

}
