package org.care.packie.data.source

interface CategoryDataSource {
    suspend fun getCategories(): List<String>

    suspend fun addCategory(category: String)

    suspend fun removeCategory(category: String)

    suspend fun editCategory(oldCategory: String, newCategory: String)
}
