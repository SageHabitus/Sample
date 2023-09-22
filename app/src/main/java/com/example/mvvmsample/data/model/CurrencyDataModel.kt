package com.example.mvvmsample.data.model

import com.example.mvvmsample.data.remote.response.CurrencyResponse

data class CurrencyDataModel(
    val applyDate: String,
    val applyTime: String,
    val currency: String,
    val tradeStanRate: Double
) {

    companion object {
        fun toDataModel(response: CurrencyResponse): CurrencyDataModel =
            with(response) {
                CurrencyDataModel(
                    applyDate = applyDate,
                    applyTime = applyTime,
                    currency = currency,
                    tradeStanRate = tradeStanRate
                )
            }
    }
}