package com.whisker.world.data.repository

import android.util.Log
import com.whisker.world.data.local.BreedDao
import com.whisker.world.data.remote.BreedApi
import com.whisker.world.domain.model.Breed
import com.whisker.world.domain.repository.BreedRepository
import okio.IOException
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val remoteDataSource: BreedApi,
    private val localDataSource: BreedDao
) : BreedRepository {

    companion object {
        private const val CLASS_NAME = "BreedRepository"
    }

    override suspend fun getAllBreeds(): Result<List<Breed>> {

        val localBreeds = localDataSource.getAll().map { it.toBreed() }

        if (localBreeds.isEmpty()) {
            // Local Data is Not Available
            try {
                val remoteResponse = remoteDataSource.getAll().execute()
                if (remoteResponse.isSuccessful) {
                    return remoteResponse.body()?.let { remoteBreeds ->
                        localDataSource.insert(remoteBreeds.map { it.toBreedEntity() })
                        return Result.success(remoteBreeds.map { it.toBreed() })
                    } ?: Result.failure(IOException())
                } else {
                    Log.e(CLASS_NAME, "Response was not successful")
                    return Result.failure(IOException())
                }
            } catch (ex: Exception) {
                Log.e(CLASS_NAME, "Failed to perform the request")
                return Result.failure(ex)
            }
        } else {
            // Local Data is Available
            Log.e(CLASS_NAME, "Local Data is available")
            return Result.success(localBreeds)
        }
    }

    override suspend fun getBreedById(id: String): Result<Breed> {
        val localBreeds = localDataSource.getById(id).toBreed()
        return Result.success(localBreeds)
    }
}
