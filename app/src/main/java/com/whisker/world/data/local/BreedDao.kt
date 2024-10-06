package com.whisker.world.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.whisker.world.data.local.entity.BreedEntity

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(breeds: List<BreedEntity>)

    @Query("SELECT * FROM breedentity")
    suspend fun getAll(): List<BreedEntity>

    @Query("SELECT * FROM breedentity WHERE id = :id")
    suspend fun getById(id: String): BreedEntity?

    @Query("SELECT * FROM breedentity WHERE name LIKE '%' || :name || '%'")
    suspend fun getByName(name: String): List<BreedEntity>

    @Query("SELECT * FROM breedentity WHERE isFavourite = :isFavourite")
    suspend fun getByFavourite(isFavourite: Boolean): List<BreedEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(breeds: List<BreedEntity>)

}
