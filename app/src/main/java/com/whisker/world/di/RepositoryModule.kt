package com.whisker.world.di

import com.whisker.world.data.repository.BreedRepositoryImpl
import com.whisker.world.data.repository.ImageRepositoryImpl
import com.whisker.world.domain.repository.BreedRepository
import com.whisker.world.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideBreedsRepository(
        breedRepositoryImpl: BreedRepositoryImpl
    ): BreedRepository

    @Binds
    @Singleton
    abstract fun provideImageRepositoryImpl(
        imageRepositoryImpl: ImageRepositoryImpl
    ): ImageRepository

}