package org.care.packie.domain

interface CategoryRepository {
    fun getCategories(): Set<String>

    fun addCategory(category: String)
}