package org.care.packie.feature.stuffs

sealed interface StuffsUiState {
    object Loading: StuffsUiState
    data class Success(
        val stuffs: Map<String, Boolean>
    ): StuffsUiState
    data class SuccessEditMode(
        val stuffs: Map<String, Boolean>
    ): StuffsUiState
    object Complete: StuffsUiState
}