package com.example.mvvmsample.data.di

import com.example.mvvmsample.data.remote.api.CurrencySerivce
import com.example.mvvmsample.data.remote.source.CurrencyRemoteDataSource
import com.example.mvvmsample.data.remote.source.CurrencyRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {

    @Provides
    @Singleton
    fun provideCurrencyRemoteDataSource(service: CurrencySerivce): CurrencyRemoteDataSource =
        CurrencyRemoteDataSourceImpl(service)
}