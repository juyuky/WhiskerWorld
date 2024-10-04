package com.whisker.world.data.remote

import com.whisker.world.data.model.Breed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedApi {

    @GET("v1/breeds")
    fun getAll(): Call<List<Breed>>

    @GET("v1/images/{imageId}")
    fun getByImageId(
        @Path("imageId") imageId: String
    ): Call<Breed>
}
