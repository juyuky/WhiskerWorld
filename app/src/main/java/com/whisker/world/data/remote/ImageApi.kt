package com.whisker.world.data.remote

import com.whisker.world.data.remote.dto.ImageDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageApi {

    @GET("v1/images/{id}")
    fun getById(@Path("id") id: String): Call<ImageDto>

}
