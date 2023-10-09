package com.example.mvvmsample.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.data.repository.CurrencyRepository
import com.example.mvvmsample.presentation.uistate.CurrencyItemUiState
import com.example.mvvmsample.presentation.uistate.CurrencyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class MainActivityViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CurrencyUiState())
    val uiState: StateFlow<CurrencyUiState> = _uiState.asStateFlow()

    fun getCurrencies(abbreviation: String = uiState.value.selectedCurrency) =
        viewModelScope.launch {
            setCurrency(abbreviation)

            val dataModels = repository
                .getCurrencies(
                    abbreviation = abbreviation,
                    date = uiState.value.selectedDate
                )
                .getOrNull() ?: return@launch

            _uiState.value = _uiState.value.copy(
                currencyItemUiState = CurrencyItemUiState.toUiState(dataModels)
            )
        }

    private fun setCurrency(abbreviation: String) {
        _uiState.value = _uiState.value.copy(selectedCurrency = abbreviation)
    }

    fun setDate(date: String) {
        _uiState.value =
            _uiState.value.copy(selectedDate = date, selectedDateIndex = dates.indexOf(date))
        getCurrencies()
    }

    companion object {
        private val dates = listOf<String>()
    }
}