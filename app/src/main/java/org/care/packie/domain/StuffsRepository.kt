package org.care.packie.domain

interface StuffsRepository {
    fun getRecommendedStuffsOf(category: String): List<String>
    fun setRecommendedStuffsOf(category: String, stuffs: List<String>)
}