package com.example.mvvmsample.data.repository

import com.example.mvvmsample.data.model.CurrencyDataModel

interface CurrencyRepository {

    suspend fun getCurrencies(abbreviation: String, date: String): Result<List<CurrencyDataModel>>
}