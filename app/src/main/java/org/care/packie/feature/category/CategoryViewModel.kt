package org.care.packie.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.care.packie.domain.CategoryRepository
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    fun addCategory(category: String) {
        viewModelScope.launch {
            categoryRepository.addCategory(category)
            getCategories()
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            val localCategories = categoryRepository.getCategories()
            _categories.emit(localCategories)
        }
    }

    fun editCategory(old: String, new: String) {
        viewModelScope.launch {
            categoryRepository.editCategory(old, new)
            getCategories()
        }
    }

    fun removeCategory(category: String) {
        viewModelScope.launch {
            categoryRepository.removeCategory(category)
            getCategories()
        }
    }
}
