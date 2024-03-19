package com.example.hwapp.di

import com.example.hwapp.data.api.NetworkRequestTracker
import com.example.hwapp.data.api.remote.NetworkStatsAPI
import com.example.hwapp.data.database.controller.PropertyController
import com.example.hwapp.data.database.dao.ImageDao
import com.example.hwapp.data.database.dao.PropertyDAO
import com.example.hwapp.data.database.repository.ImageRepository
import com.example.hwapp.data.database.repository.PropertyDBRepository
import com.example.hwapp.domain.api.repository.PropertyApiRepo
import com.example.hwapp.domain.database.repository.ImageRepo
import com.example.hwapp.domain.database.repository.PropertyDBRepo
import com.example.hwapp.data.api.remote.PropertyApi
import com.example.hwapp.data.api.repository.NetworkStatsAPIRepository
import com.example.hwapp.data.api.repository.PropertyApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideImageRepository(imageDao: ImageDao): ImageRepo {
        return ImageRepository(imageDao)
    }

    @Provides
    @ViewModelScoped
    fun provideNetworkStatsApiRepository(networkStatsAPI: NetworkStatsAPI): NetworkStatsAPIRepository {
        return NetworkStatsAPIRepository(networkStatsAPI)
    }

    @Provides
    @ViewModelScoped
    fun providePropertyApiRepository(networkRequestTracker: NetworkRequestTracker, networkStatsAPIRepository: NetworkStatsAPIRepository): PropertyApiRepo {
        return PropertyApiRepository(networkRequestTracker, networkStatsAPIRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideNetworkRequestTracker(propertyApi: PropertyApi): NetworkRequestTracker {
        return NetworkRequestTracker(propertyApi)
    }

    @Provides
    @ViewModelScoped
    fun providePropertyRepository(propertyDAO: PropertyDAO): PropertyDBRepo {
        return PropertyDBRepository(propertyDAO)
    }

    @Provides
    @ViewModelScoped
    fun providePropertyController(
        propertyRepository: PropertyDBRepo,
        imageRepository: ImageRepo
    ): PropertyController {
        return PropertyController(propertyRepository, imageRepository)
    }
}