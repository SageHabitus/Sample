package com.example.mvvmsample.data.remote.api

import com.example.mvvmsample.data.remote.response.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencySerivce {

    @GET("{abbreviation}/{date}")
    suspend fun getCurrencies(
        @Path("abbreviation") abbreviation: String,
        @Path("date") date: String
    ): List<CurrencyResponse>
}