package com.example.mobiletraining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object DashboardViewModel : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState>
        get() = _state

    init {
        viewModelScope.launch{
            delay(3000)
            _state.value = _state.value.copy(
                isTextLoading = false
            )
            delay(2000)
            _state.value = _state.value.copy(
                isDataLoading = false,
                isSpecsLoading = false
            )
            delay(3000)
            _state.value = _state.value.copy(
                isImageLoading = false,
                isFooterLoading = false
            )
        }
    }

    fun bookDestination(booked: Boolean = true) {
        _state.value = _state.value.copy(
            booked = booked
        )
    }
}

data class DashboardState(
    val isImageLoading: Boolean = true,
    val isDataLoading: Boolean = true,
    val isSpecsLoading: Boolean = true,
    val isTextLoading: Boolean = true,
    val isFooterLoading: Boolean = true,

    val booked: Boolean = false
)