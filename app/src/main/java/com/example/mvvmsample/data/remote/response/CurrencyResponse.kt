package com.example.mvvmsample.data.remote.response


import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("applyDate")
    val applyDate: String,
    @SerializedName("applyTime")
    val applyTime: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("tradeStanRate")
    val tradeStanRate: Double
)