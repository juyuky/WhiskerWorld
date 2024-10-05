package com.whisker.world.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey
    val id: String,
    val url: String?
) {
}