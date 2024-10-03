package com.whisker.world.di

import com.whisker.world.data.remote.BreedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBreedsApi(retrofit: Retrofit): BreedApi {
        return retrofit.create(BreedApi::class.java)
    }
}