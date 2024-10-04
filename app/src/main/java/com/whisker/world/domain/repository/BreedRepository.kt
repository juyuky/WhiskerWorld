package com.whisker.world.domain.repository

import com.whisker.world.data.model.Breed

interface BreedRepository {

    fun getAllBreeds(): Result<List<Breed>>

    fun getBreedByImageId(imageId: String): Result<Breed>

}
