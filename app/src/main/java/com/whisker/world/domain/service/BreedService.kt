package com.whisker.world.domain.service

import com.whisker.world.data.model.Breed

interface BreedService {

    suspend fun getAllBreeds(): Result<List<Breed>>
}