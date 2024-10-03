package com.whisker.world.data.model

import com.google.gson.annotations.SerializedName

data class Breed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    @SerializedName("reference_image_id")
    val imageId: String
)
