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
            Log.i(CLASS_NAME, "Local Data is available")
            return Result.success(localBreeds)
        }
    }


    override suspend fun getBreedById(id: String): Result<Breed> {
        val localBreedEntity = localDataSource.getById(id)
        return if (localBreedEntity != null) {
            Result.success(localBreedEntity.toBreed())
        } else {
            Log.i(CLASS_NAME, "Failed to get breed by id: $id")
            Result.failure(IOException())
        }
    }

    override suspend fun getBreedsByName(name: String): Result<List<Breed>> {
        val localBreedEntities = localDataSource.getByName(name)
        return if (localBreedEntities.isNotEmpty()) {
            Result.success(localBreedEntities.map { it.toBreed() })
        } else {
            // TODO LAST CHECK IF SHOULD RETURN FAILURE
            Log.i(CLASS_NAME, "Failed to get breed by name: $name")
            Result.failure(IOException())
        }
    }

    override suspend fun getFavouriteBreeds(): Result<List<Breed>> {
        val localFavouriteBreedsEntities =
            localDataSource.getByFavourite(true)
        return if (localFavouriteBreedsEntities.isNotEmpty()) {
            Result.success(localFavouriteBreedsEntities.map { it.toBreed() })
        } else {
            Log.i(CLASS_NAME, "Failed to get favourite breeds")
            Result.failure(IOException())
        }
    }

    override suspend fun updateBreeds(breeds: List<Breed>): Result<List<Breed>> {
        if (breeds.isEmpty()) {
            Log.i(CLASS_NAME, "Failed to update breeds. List cannot be empty.")
            return Result.failure(IOException())
        }
        val localBreedEntity = breeds.map { it.toBreedEntity() }
        // Update local data base
        localDataSource.update(localBreedEntity)
        // Get updated list
        localDataSource.getAll().first { it.id == breeds.first().id }
        return Result.success(localDataSource.getAll().map { it.toBreed() })
    }
}
