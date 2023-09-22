package com.example.mvvmsample.data.repository

import com.example.mvvmsample.data.model.CurrencyDataModel
import com.example.mvvmsample.data.remote.source.CurrencyRemoteDataSource
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remote: CurrencyRemoteDataSource
) : CurrencyRepository {

    override suspend fun getCurrencies(
        abbreviation: String,
        date: String
    ): Result<List<CurrencyDataModel>> =
        runCatching {
            remote.getCurrencies(abbreviation = abbreviation, date = date)
        }
}