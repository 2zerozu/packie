package org.care.packie.feature.stuffs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.care.packie.domain.StuffsRepository
import javax.inject.Inject

@HiltViewModel
class StuffsViewModel @Inject constructor(
    private val stuffsRepository: StuffsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<StuffsUiState>(StuffsUiState.Loading)
    val uiState: StateFlow<StuffsUiState> = _uiState

    private val savedStuffs = mutableMapOf<String, Boolean>()
    private val mutableStuffs = mutableMapOf<String, Boolean>()

    fun getStuffs(category: String) = viewModelScope.launch {
        _uiState.value = StuffsUiState.Loading
        val stuffs = stuffsRepository.getRecommendedStuffsOf(category)
        emitStuffs(stuffs)

    }

    private suspend fun emitStuffs(stuffs: List<String>) {
        stuffs.associateWith { false }.let {
            savedStuffs.clear()
            savedStuffs.putAll(it)
            _uiState.emit(
                StuffsUiState.Success(
                    stuffs = savedStuffs.toMap()
                )
            )
        }
    }

    fun enableEditMode() {
        mutableStuffs.clear()
        mutableStuffs.putAll(savedStuffs)
        _uiState.value = StuffsUiState.SuccessEditMode(
            stuffs = mutableStuffs.toMap()
        )

    }

    fun disableEditMode() {
        _uiState.value = StuffsUiState.Success(
            stuffs = savedStuffs.toMap()
        )

    }

    fun checkStuffs(stuff: String) {
        savedStuffs[stuff]?.let {
            savedStuffs[stuff] = it.not()
            updateSavedStuffsOnCheck()
        }
        if (!savedStuffs.containsValue(false)) {
            _uiState.value = StuffsUiState.Complete
        }
    }

    private fun updateSavedStuffsOnCheck() {
        _uiState.update { prevState ->
            check(prevState is StuffsUiState.Success)
            prevState.copy(
                stuffs = savedStuffs.toMap()
            )
        }
    }

    fun addStuff(stuff: String): Result<Unit> {
        if (mutableStuffs.containsKey(stuff)) {
            return Result.failure(IllegalStateException())
        }
        mutableStuffs[stuff] = false
        updateMutableStuffs()
        return Result.success(Unit)

    }

    fun removeStuff(stuff: String): Result<Unit> {
        if (!mutableStuffs.containsKey(stuff)) {
            return Result.failure(IllegalStateException())
        }
        mutableStuffs.remove(stuff)
        updateMutableStuffs()
        return Result.success(Unit)
    }

    private fun updateMutableStuffs() {
        _uiState.update { prevState ->
            check(prevState is StuffsUiState.SuccessEditMode)
            prevState.copy(
                stuffs = mutableStuffs.toMap()
            )
        }
    }

    fun saveStuffs(category: String) = viewModelScope.launch {
        stuffsRepository.setRecommendedStuffsOf(
            category = category,
            stuffs = mutableStuffs.keys.toList()
        )
        getStuffs(category)
    }

}