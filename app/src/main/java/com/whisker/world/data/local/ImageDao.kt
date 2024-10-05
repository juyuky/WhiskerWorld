package com.whisker.world.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whisker.world.data.local.entity.ImageEntity

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imagesEntities: List<ImageEntity>)

    @Query("SELECT * FROM imageentity")
    suspend fun getAll(): List<ImageEntity>

    @Query("SELECT * FROM imageentity WHERE id = :id")
    suspend fun getById(id: String): ImageEntity

}
