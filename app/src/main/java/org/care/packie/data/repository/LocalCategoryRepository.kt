package org.care.packie.data.repository

import org.care.packie.data.source.LocalCategoryDataSource
import org.care.packie.domain.CategoryRepository
import javax.inject.Inject

class LocalCategoryRepository @Inject constructor(
    private val localCategoryDataSource: LocalCategoryDataSource
) : CategoryRepository {
    override fun getCategories(): Set<String> = localCategoryDataSource.categories

    override fun addCategory(category: String) {
        val updatedCategories = localCategoryDataSource.categories.toMutableSet()
        updatedCategories.add(category)
        localCategoryDataSource.categories = updatedCategories
    }

    override fun editCategory(old: String, new: String) {
        val updatedCategories = localCategoryDataSource.categories.toMutableSet()
        if (updatedCategories.contains(old)) {
            updatedCategories.remove(old)
            updatedCategories.add(new)
            localCategoryDataSource.categories = updatedCategories
        }
    }

    override fun deleteCategory(category: String) {
        val updatedCategories = localCategoryDataSource.categories.toMutableSet()
        updatedCategories.remove(category)
        localCategoryDataSource.categories = updatedCategories
    }
}
