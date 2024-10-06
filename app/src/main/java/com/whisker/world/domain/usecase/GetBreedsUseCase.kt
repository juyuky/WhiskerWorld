package com.whisker.world.domain.usecase

import android.util.Log
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
            breedRepository.getAllBreeds().mapCatching { breeds ->
                val imageIds: List<String> = breeds.mapNotNull { it.imageId }
                val breedMap = breeds.associateBy { it.imageId }

                imageRepository.getAllImages(imageIds).onSuccess { imageEntities ->
                    val breedsWithUrl = imageEntities.mapNotNull { entity ->
                        breedMap[entity.id]?.copy(imageUrl = entity.url)
                    }

                    val breedsToUpdate = breedsWithUrl.filter { updatedBreed ->
                        val originalBreed = breedMap[updatedBreed.imageId]
                        originalBreed == null || originalBreed.imageUrl != updatedBreed.imageUrl
                    }

                    if (breedsToUpdate.isNotEmpty()) {
                        breedRepository.updateBreeds(breedsToUpdate)
                    }
                    return@withContext Result.success(breedsWithUrl)
                }.onFailure { error ->
                    Log.e("GetBreedsUseCase", "Error fetching images: $error")
                    return@withContext Result.failure(error)
                }
            }
            Result.success(emptyList())
        }
}
