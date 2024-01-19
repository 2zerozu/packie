package org.care.packie.domain

import kotlinx.serialization.Serializable

@Serializable
data class Stuff(
    val name: String,
    var isChecked: Boolean
)
