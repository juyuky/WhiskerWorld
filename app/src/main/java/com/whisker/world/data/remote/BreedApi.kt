package com.whisker.world.data.remote

import com.whisker.world.data.remote.dto.BreedDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedApi {

    @GET("v1/breeds")
    fun getAll(): Call<List<BreedDto>>

    @GET("v1/images/{imageId}")
    fun getByImageId(@Path("imageId") imageId: String): Call<BreedDto>
}
