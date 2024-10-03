package com.whisker.world.data.repository

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.whisker.world.data.model.Breed
import com.whisker.world.data.remote.BreedApi
import com.whisker.world.domain.repository.BreedRepository
import okio.IOException
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val breedApi: BreedApi
) : BreedRepository {

    override fun getAllBreeds(): Result<List<Breed>> {
        try {
            val response = breedApi.getAll().execute()
            return if (response.isSuccessful) {
                return response.body()?.let {
                    return Result.success(it)
                } ?: Result.failure(IOException())
            } else {
                Log.e("BreedRepository","Response was not successful")
                Result.failure(IOException())
            }
        } catch (e: IOException) {
            Log.e("BreedRepository","Failed to perform the request")
            return Result.failure(e)
        }
    }
}
