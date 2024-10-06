package com.whisker.world.domain.repository

import com.whisker.world.domain.model.Breed

interface BreedRepository {

    suspend fun getAllBreeds(): Result<List<Breed>>

    suspend fun getBreedById(id: String): Result<Breed>

    suspend fun getBreedsByName(name: String): Result<List<Breed>>

    suspend fun getFavouriteBreeds(): Result<List<Breed>>

    suspend fun updateBreeds(breeds: List<Breed>): Result<List<Breed>>
}
