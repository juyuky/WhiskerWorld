package com.whisker.world.di

import com.whisker.world.data.remote.BreedApi
import com.whisker.world.data.remote.ImageApi
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

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): ImageApi {
        return retrofit.create(ImageApi::class.java)
    }
}