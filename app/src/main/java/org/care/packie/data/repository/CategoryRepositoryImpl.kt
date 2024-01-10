package org.care.packie.data.repository

import org.care.packie.data.source.LocalCategoryDataSource
import org.care.packie.domain.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val localCategoryDataSource: LocalCategoryDataSource
) : CategoryRepository {
    override fun getCategories(): Set<String> = localCategoryDataSource.categories


    override fun addCategory(category: String) {
        localCategoryDataSource.categories.plus(category)
    }
}