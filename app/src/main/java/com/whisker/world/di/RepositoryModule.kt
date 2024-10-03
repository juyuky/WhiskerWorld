package com.whisker.world.di

import com.whisker.world.data.repository.BreedRepositoryImpl
import com.whisker.world.domain.repository.BreedRepository
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
}