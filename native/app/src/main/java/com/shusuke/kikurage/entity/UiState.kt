package com.shusuke.kikurage.entity

sealed class UiState<out T> {
    data class Success<out T>(val value: T) : UiState<T>()
    data class Error(val error: Throwable) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}