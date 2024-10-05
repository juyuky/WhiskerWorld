package com.whisker.world.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whisker.world.data.local.entity.BreedEntity

@Dao
interface BreedDao {
    // TODO USE REPLACE OR IGNORE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(breeds: List<BreedEntity>)

    @Query("SELECT * FROM breedentity")
    suspend fun getAll(): List<BreedEntity>

    @Query("SELECT * FROM breedentity where id = :id")
    suspend fun getById(id: String): BreedEntity
}
