package org.care.packie.feature.category

import android.util.Log
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

//    init {
//        getCategories()
//    }

    private val _categories = MutableStateFlow<Set<String>>(emptySet())
    val categories = _categories.asStateFlow()

    fun addCategory(category: String) {
        categoryRepository.addCategory(category)
        Log.d("asdf", "$category 가 추가됨")
    }

    fun getCategories() {
        /** TODO 로컬에서 카테고리 가져오는 로직 구현 */
        viewModelScope.launch {
            val localCategories = categoryRepository.getCategories()
            _categories.emit(localCategories)
            Log.d("asdf", "${_categories.value}")
        }
    }
}
