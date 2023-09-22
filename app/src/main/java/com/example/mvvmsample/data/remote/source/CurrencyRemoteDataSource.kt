package com.example.mvvmsample.data.remote.source

import com.example.mvvmsample.data.model.CurrencyDataModel

interface CurrencyRemoteDataSource {

    suspend fun getCurrencies(abbreviation: String, date: String): List<CurrencyDataModel>
}