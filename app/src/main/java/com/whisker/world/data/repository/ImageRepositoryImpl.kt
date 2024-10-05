package com.whisker.world.data.repository

import android.util.Log
import com.whisker.world.data.local.ImageDao
import com.whisker.world.data.local.entity.ImageEntity
import com.whisker.world.data.remote.ImageApi
import com.whisker.world.domain.repository.ImageRepository
import java.io.IOException
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val remoteDataSource: ImageApi,
    private val localDataSource: ImageDao
) : ImageRepository {

    override suspend fun getAllImages(imagesIds: List<String>): Result<List<ImageEntity>> {
        val localImages = localDataSource.getAll()

        if (localImages.isEmpty()) {
            // Local Data is Not Available
            try {
                val entitiesList = mutableListOf<ImageEntity>()
                imagesIds.forEach { imageId ->
                    val remoteResponse = remoteDataSource.getById(imageId).execute()
                    if (remoteResponse.isSuccessful) {
                        remoteResponse.body()?.let {
                            entitiesList.add(it.toImageEntity())
                        }
                    } else {
                        Log.e("ImageRepository", "Failed to get image with id $imageId")
                        return Result.failure(IOException())
                    }
                }

                // Insert all data on table
                localDataSource.insert(entitiesList)
                return Result.success(localImages)

            } catch (ex: Exception) {
                Log.e("ImageRepository", "Failed to perform the request")
                return Result.failure(ex)

            }
        } else {
            // Local Data is Available
            Log.e("ImageRepository", "Local Data is available")
            return Result.success(localImages)
        }
    }

}