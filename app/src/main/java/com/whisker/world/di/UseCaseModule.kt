package com.whisker.world.di

import com.whisker.world.domain.repository.BreedRepository
import com.whisker.world.domain.usecase.GetBreedByIdUseCase
import com.whisker.world.domain.usecase.GetBreedsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetBreedsUseCase(
        breedRepository: BreedRepository
    ): GetBreedsUseCase {
        return GetBreedsUseCase(breedRepository)
    }

    @Provides
    fun provideGetBreedByIdUseCase(
        breedRepository: BreedRepository
    ): GetBreedByIdUseCase {
        return GetBreedByIdUseCase(breedRepository)
    }
}
