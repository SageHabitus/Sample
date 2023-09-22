package com.example.mvvmsample.presentation.uistate

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvvmsample.data.model.CurrencyDataModel
import java.time.LocalTime

data class CurrencyItemUiState(
    val applyDate: String,
    val applyTime: String,
    val tradeStanRate: String
) {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun toUiState(dataModels: List<CurrencyDataModel>): List<CurrencyItemUiState> =
            dataModels
                .filter {
                    val applyTime = LocalTime.parse(it.applyTime)
                    applyTime >= LocalTime.of(9, 0) && applyTime <= LocalTime.of(15, 0)
                }
                .map {
                    CurrencyItemUiState(
                        applyDate = it.applyDate,
                        applyTime = it.applyTime,
                        tradeStanRate = it.tradeStanRate.toString()
                    )
                }
    }
}
