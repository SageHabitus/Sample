package com.example.mvvmsample.presentation.uistate

data class CurrencyUiState(
    val selectedCurrency: String = DEFAULT_CURRENCY,
    val selectedDate: String = DEFAULT_DATE,
    val selectedDateIndex: Int = DEFAULT_INDEX,
    val currencyItemUiState: List<CurrencyItemUiState> = emptyList()
) {

    companion object {
        private const val DEFAULT_CURRENCY = "usd"
        private const val DEFAULT_DATE = ""
        private const val DEFAULT_INDEX = 0
    }
}