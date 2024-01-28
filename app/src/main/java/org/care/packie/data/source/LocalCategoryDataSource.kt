package org.care.packie.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LocalCategoryDataSource @Inject constructor(
    private val prefs: SharedPreferences,
    private val json: Json
) : CategoryDataSource {
    override suspend fun getCategories(): Set<String> = withContext(Dispatchers.IO) {
        val categoriesJson = prefs.getString(CATEGORIES, null)
        return@withContext categoriesJson?.let { json.decodeFromString<Set<String>>(it) }
            ?: emptySet()
    }

    override suspend fun addCategory(category: String) = withContext(Dispatchers.IO) {
        val categories = getCategories().toMutableList()
        categories.add(category)
        prefs.edit {
            putString(CATEGORIES, json.encodeToString(categories))
        }
    }

    override suspend fun removeCategory(category: String) = withContext(Dispatchers.IO) {
        val categories = getCategories().toMutableList()
        categories.remove(category)
        prefs.edit {
            putString(CATEGORIES, json.encodeToString(categories))
        }
    }

    override suspend fun editCategory(oldCategory: String, newCategory: String) =
        withContext(Dispatchers.IO) {
            val categories = getCategories().toMutableList()
            val index = categories.indexOf(oldCategory)
            if (index != -1) {
                categories[index] = newCategory
                prefs.edit {
                    putString(CATEGORIES, json.encodeToString(categories))
                }
            } else {
                throw Exception("Category not found")
            }
        }

    companion object {
        private const val CATEGORIES = "categories"
    }
}
