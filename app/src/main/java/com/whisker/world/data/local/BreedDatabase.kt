package com.whisker.world.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whisker.world.data.local.entity.BreedEntity

@Database(entities = [BreedEntity::class], version = 1)
abstract class BreedDatabase : RoomDatabase() {

    abstract val breedDao: BreedDao
}
