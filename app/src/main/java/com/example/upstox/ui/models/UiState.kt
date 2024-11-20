package com.example.upstox.ui.models

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val errorText: String) : UiState<Nothing>
    object Loading : UiState<Nothing>
}