package com.thezayin.dadjokes.screens.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkJoke(
    @SerialName("id") val id: String,
    @SerialName("joke") val content: String,
    @SerialName("status") val statusCode: Int
)