package org.care.packie.data.repository

import org.care.packie.data.source.CategoryDataSource
import org.care.packie.domain.CategoryRepository
import javax.inject.Inject

class LocalCategoryRepository @Inject constructor(
    private val categoryDataSource: CategoryDataSource
) : CategoryRepository {
    override suspend fun getCategories(): List<String> = categoryDataSource.getCategories()

    override suspend fun addCategory(category: String) = categoryDataSource.addCategory(category)

    override suspend fun removeCategory(category: String) =
        categoryDataSource.removeCategory(category)

    override suspend fun editCategory(oldCategory: String, newCategory: String) =
        categoryDataSource.editCategory(oldCategory, newCategory)
}
