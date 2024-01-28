package org.care.packie.feature.category

sealed interface CategoryUiState {
    object Loading : CategoryUiState

    data class Success(
        val categories: List<String>
    ) : CategoryUiState
}
