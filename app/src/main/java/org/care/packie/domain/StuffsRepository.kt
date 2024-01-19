package org.care.packie.domain

interface StuffsRepository {
    suspend fun getRecommendedStuffsOf(category: String): List<String>
    suspend fun setRecommendedStuffsOf(category: String, stuffs: List<String>)
}