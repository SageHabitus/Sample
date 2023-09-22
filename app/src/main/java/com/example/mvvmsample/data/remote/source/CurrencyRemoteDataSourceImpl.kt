package com.example.mvvmsample.data.remote.source

import com.example.mvvmsample.data.model.CurrencyDataModel
import com.example.mvvmsample.data.remote.api.CurrencySerivce
import javax.inject.Inject

class CurrencyRemoteDataSourceImpl @Inject constructor(
    private val service: CurrencySerivce
) : CurrencyRemoteDataSource {

    override suspend fun getCurrencies(
        abbreviation: String,
        date: String
    ): List<CurrencyDataModel> =
        service.getCurrencies(abbreviation = abbreviation, date = date)
            .map(CurrencyDataModel::toDataModel)

}