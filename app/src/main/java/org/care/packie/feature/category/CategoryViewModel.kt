package org.care.packie.feature.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    /** TODO 로컬 구현 후 제거 */
    private val localCategoryExample = mutableListOf("출근", "놀러갈 때", "출장", "장볼 때", "여행")

    fun addCategory(category: String) {
        /** TODO 로컬에 카테고리 추가하는 로직 구현 */
        localCategoryExample.add(category)
        Log.d("asdf", "$category 가 추가됨")
    }

    fun getCategories() {
        /** TODO 로컬에서 카테고리 가져오는 로직 구현 */
        viewModelScope.launch {
            _categories.emit(localCategoryExample)
            Log.d("asdf", "${_categories.value}")
        }
    }
}
