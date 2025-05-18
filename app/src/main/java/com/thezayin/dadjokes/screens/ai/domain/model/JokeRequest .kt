package com.thezayin.dadjokes.screens.ai.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AIRequestWithDetails(
    val type: String, val format: String, val description: String? = ""
)

@Serializable
data class AIRequestWithDescription(
    val description: String
)

@Serializable
data class AIResponse(
    val candidates: List<AICandidate>
)

@Serializable
data class AICandidate(
    val content: AIContent
)

@Serializable
data class AIContent(
    val parts: List<AIPart>
)

@Serializable
data class AIPart(
    val text: String
)

data class AIContentModel(
    val content: String,
    val title: String = "",
    val description: String = "",
    val additionalInfo: String = ""
)