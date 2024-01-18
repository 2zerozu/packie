package org.care.packie.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LocalStuffsDataSource @Inject constructor(
    private val prefs: SharedPreferences
): StuffsDataSource {
    override fun getRecommendedStuffsOf(category: String): String? {
        return prefs.getString(category, null)
    }

    override fun saveRecommendedStuffsOf(category: String, stuffsJson: String) {
        prefs.edit {
            putString(category, stuffsJson)
        }
    }
}