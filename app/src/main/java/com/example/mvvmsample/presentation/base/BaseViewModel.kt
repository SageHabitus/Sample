package com.example.mvvmsample.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<STATE : BaseUiState>(defaultState: STATE) : ViewModel() {

    private val _uiState = MutableStateFlow(defaultState)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    fun ViewModel.launch(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

        return this.viewModelScope.launch(context + exceptionHandler, block = block)
    }

    protected fun updateState(transform: STATE.() -> STATE) {
        val newValue = _uiState.value.transform()
        if (_uiState.value != newValue) {
            _uiState.value = newValue
        }
    }

}