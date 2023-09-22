package com.example.mvvmsample.presentation.uistate

import com.example.mvvmsample.presentation.base.BaseUiState

data class CurrencyUiState(
    val selectedCurrency: String = DEFAULT_CURRENCY,
    val selectedDate: String = DEFAULT_DATE,
    val selectedDateIndex: Int = DEFAULT_INDEX,
    val currencyItemUiState: List<CurrencyItemUiState> = emptyList()
) : BaseUiState {

    companion object {
        private const val DEFAULT_CURRENCY = "usd"
        private const val DEFAULT_DATE = "2023-09-04"
        private const val DEFAULT_INDEX = 0
    }
}