package org.care.packie.data.repository

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.care.packie.data.source.StuffsDataSource
import org.care.packie.domain.StuffsRepository
import javax.inject.Inject

class LocalStuffsRepository @Inject constructor(
    private val stuffsDataSource: StuffsDataSource,
    private val json: Json
): StuffsRepository {
    override fun getRecommendedStuffsOf(category: String): List<String> {
        return stuffsDataSource.getRecommendedStuffsOf(category)?.let {
            json.decodeFromString<List<String>>(it)
        } ?: emptyList()
    }

    override fun setRecommendedStuffsOf(category: String, stuffs: List<String>) {
       val stuffsJson = json.encodeToString(stuffs)
        stuffsDataSource.saveRecommendedStuffsOf(category, stuffsJson)
    }
}