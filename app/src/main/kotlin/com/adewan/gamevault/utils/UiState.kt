package com.adewan.gamevault.utils

sealed interface UiState {
  data object Initial : UiState

  data object Loading : UiState

  data class Error(val exception: Exception) : UiState

  data class Result<T>(val data: T) : UiState
}
