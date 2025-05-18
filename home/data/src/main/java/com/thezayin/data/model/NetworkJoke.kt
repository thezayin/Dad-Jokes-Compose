package com.thezayin.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for API response mapping.
 */
@Serializable
data class NetworkJoke(
    @SerialName("id") val id: String,
    @SerialName("joke") val content: String,
    @SerialName("status") val statusCode: Int
)