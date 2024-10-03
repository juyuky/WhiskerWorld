package com.whisker.world.data.remote

import com.whisker.world.data.model.Breed
import retrofit2.Call
import retrofit2.http.GET

interface BreedApi {

    @GET("v1/breeds")
    fun getAll(): Call<List<Breed>>
}
