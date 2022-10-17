package com.example.checkbox

sealed class MainViewState {
    data class ActivateRadio(val isActive : Boolean) : MainViewState()
    object ClearRadio : MainViewState()
}