package com.example.mvvmsample.data.di

import com.example.mvvmsample.data.remote.api.CurrencySerivce
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideCurrencyService(
        retrofit: Retrofit
    ): CurrencySerivce = retrofit.create(CurrencySerivce::class.java)
}