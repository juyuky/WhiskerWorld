package com.whisker.world.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whisker.world.data.local.entity.BreedEntity
import com.whisker.world.data.local.entity.ImageEntity

@Database(entities = [BreedEntity::class, ImageEntity::class], version = 1)
abstract class BreedDatabase : RoomDatabase() {

    abstract val breedDao: BreedDao

    abstract val imageDao: ImageDao
}
