package com.whisker.world.data.remote

import com.whisker.world.data.remote.dto.BreedDto
import retrofit2.Call
import retrofit2.http.GET

interface BreedApi {

    @GET("v1/breeds")
    fun getAll(): Call<List<BreedDto>>

}
