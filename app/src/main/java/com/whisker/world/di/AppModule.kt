package com.whisker.world.di

import android.content.Context
import androidx.room.Room
import com.whisker.world.data.local.BreedDao
import com.whisker.world.data.local.BreedDatabase
import com.whisker.world.data.local.ImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.thecatapi.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideBreedsDatabase(@ApplicationContext context: Context): BreedDatabase {
        return Room.databaseBuilder(
            context, BreedDatabase::class.java, "whisker_db"
        ).build()
    }

    @Provides
    fun provideBreadDAO(appDatabase: BreedDatabase): BreedDao {
        return appDatabase.breedDao
    }

    @Provides
    fun provideImageDAO(appDatabase: BreedDatabase): ImageDao {
        return appDatabase.imageDao
    }
}
