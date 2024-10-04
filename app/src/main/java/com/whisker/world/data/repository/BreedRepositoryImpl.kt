package com.whisker.world.data.repository

import android.util.Log
import com.whisker.world.data.model.Breed
import com.whisker.world.data.remote.BreedApi
import com.whisker.world.domain.repository.BreedRepository
import okio.IOException
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val breedApi: BreedApi
) : BreedRepository {

    companion object {
        private const val CLASS_NAME = "BreedRepository"
    }

    override fun getAllBreeds(): Result<List<Breed>> {
        try {
            val response = breedApi.getAll().execute()
            return if (response.isSuccessful) {
                return response.body()?.let {
                    return Result.success(it)
                } ?: Result.failure(IOException())
            } else {
                Log.e(CLASS_NAME, "Response was not successful")
                Result.failure(IOException())
            }
        } catch (e: IOException) {
            Log.e(CLASS_NAME, "Failed to perform the request")
            return Result.failure(e)
        }
    }

    override fun getBreedByImageId(imageId: String): Result<Breed> {
        try {
            val response = breedApi.getByImageId(imageId).execute()
            return if (response.isSuccessful) {
                return response.body()?.let {
                    return Result.success(it)
                } ?: Result.failure(IOException())
            } else {
                Log.e(CLASS_NAME, "Response was not successful")
                Result.failure(IOException())
            }
        } catch (e: IOException) {
            Log.e(CLASS_NAME, "Failed to perform the request")
            return Result.failure(e)
        }
    }
}
