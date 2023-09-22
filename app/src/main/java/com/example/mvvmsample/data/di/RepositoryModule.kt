package com.example.mvvmsample.data.di

import com.example.mvvmsample.data.remote.source.CurrencyRemoteDataSource
import com.example.mvvmsample.data.repository.CurrencyRepository
import com.example.mvvmsample.data.repository.CurrencyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(remote : CurrencyRemoteDataSource) : CurrencyRepository =
        CurrencyRepositoryImpl(remote)
}