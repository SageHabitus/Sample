package com.example.mvvmsample.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvvmsample.data.repository.CurrencyRepository
import com.example.mvvmsample.presentation.base.BaseViewModel
import com.example.mvvmsample.presentation.uistate.CurrencyItemUiState
import com.example.mvvmsample.presentation.uistate.CurrencyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class MainActivityViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : BaseViewModel<CurrencyUiState>(CurrencyUiState()) {

    fun getCurrencies(abbreviation: String = uiState.value.selectedCurrency) = launch {
        setCurrency(abbreviation)

            val dataModels = repository
                .getCurrencies(
                    abbreviation = abbreviation,
                    date = uiState.value.selectedDate
                )
                .getOrNull() ?: return@launch

            withContext(Dispatchers.Main) {
                updateState { copy(currencyItemUiState = CurrencyItemUiState.toUiState(dataModels)) }
            }
        }

    private fun setCurrency(abbreviation: String) {
        updateState { copy(selectedCurrency = abbreviation) }
    }

    fun setDate(date: String) {
        updateState { copy(selectedDate = date, selectedDateIndex = dates.indexOf(date)) }
        getCurrencies()
    }

    companion object {
        private val dates = listOf(
            "2023-09-04",
            "2023-09-05",
            "2023-09-06",
            "2023-09-07",
            "2023-09-08"
        )
    }
}