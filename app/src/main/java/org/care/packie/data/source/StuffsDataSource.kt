package org.care.packie.data.source

interface StuffsDataSource {
    suspend fun getRecommendedStuffsOf(category: String): String?
    suspend fun saveRecommendedStuffsOf(category: String, stuffsJson: String)
    suspend fun clear(category: String)
}