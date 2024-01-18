package org.care.packie.data.source

interface StuffsDataSource {
    fun getRecommendedStuffsOf(category: String): String?
    fun saveRecommendedStuffsOf(category: String, stuffsJson: String)
}