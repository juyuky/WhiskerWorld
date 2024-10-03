package com.whisker.world.di

import com.whisker.world.data.service.BreedServiceImpl
import com.whisker.world.domain.repository.BreedRepository
import com.whisker.world.domain.service.BreedService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideBreedsService(breedRepository: BreedRepository): BreedService {
        return BreedServiceImpl(breedRepository)
    }
}
