package org.care.packie.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalStuffsDataSource @Inject constructor(
    private val prefs: SharedPreferences
) : StuffsDataSource {
    override suspend fun getRecommendedStuffsOf(
        category: String
    ): String? = withContext(Dispatchers.IO) {
        return@withContext prefs.getString(category, null)
    }

    override suspend fun saveRecommendedStuffsOf(
        category: String,
        stuffsJson: String
    ) = withContext(Dispatchers.IO) {
        prefs.edit {
            putString(category, stuffsJson)
        }
    }

    override suspend fun clear(
        category: String
    ) = withContext(Dispatchers.IO) {
        prefs.edit {
            remove(category)
        }
    }
}