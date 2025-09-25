package com.example.microformastest.data.di

import com.example.microformastest.data.api.ApiService
import com.example.microformastest.data.repository.AuthRepositoryImpl
import com.example.microformastest.data.repository.ServiceRepositoryImpl
import com.example.microformastest.domain.DataStorage_plus_Tink.DataStoreManager
import com.example.microformastest.domain.interfaces.AuthRepository
import com.example.microformastest.domain.interfaces.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideServicesRepository(
        apiService: ApiService,
        dataStore: DataStoreManager
    ): ServiceRepository {
        return ServiceRepositoryImpl(apiService, dataStore)
    }
}