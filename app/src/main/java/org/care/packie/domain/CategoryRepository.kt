package org.care.packie.domain

interface CategoryRepository {
    fun getCategories(): Set<String>

    fun addCategory(category: String)

    fun editCategory(old: String, new: String)

    fun deleteCategory(category: String)
}
