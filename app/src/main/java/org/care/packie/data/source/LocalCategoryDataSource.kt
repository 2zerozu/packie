package org.care.packie.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LocalCategoryDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {
    var categories: Set<String>
        set(value) = prefs.edit { putStringSet(CATEGORIES, value) }
        get() = prefs.getStringSet(CATEGORIES, emptySet()) ?: emptySet()

    companion object {
        private const val CATEGORIES = "categories"
    }
}
