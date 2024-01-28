package org.care.packie.domain

interface CategoryRepository {
    suspend fun getCategories(): List<String>
    suspend fun addCategory(category: String)
    suspend fun removeCategory(category: String)
    suspend fun editCategory(oldCategory: String, newCategory: String)
}
