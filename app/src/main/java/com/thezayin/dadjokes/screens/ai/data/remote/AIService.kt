package com.thezayin.dadjokes.screens.ai.data.remote

import com.thezayin.dadjokes.screens.ai.domain.model.AIRequestWithDescription
import com.thezayin.dadjokes.screens.ai.domain.model.AIRequestWithDetails
import com.thezayin.dadjokes.screens.ai.domain.model.AIResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

class AIService {

    companion object {
        private const val TIME_MILLS = 60000L
        private val AI_API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyDuCKDWecLo7a0z3GhvlOKGvanT-WT75S4"
    }

    private val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    private val client = HttpClient(Android) {
        install(HttpTimeout) {
            socketTimeoutMillis = TIME_MILLS
            requestTimeoutMillis = TIME_MILLS
            connectTimeoutMillis = TIME_MILLS
        }

        install(ContentNegotiation) { json(json) }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.tag("Response").i(message)
                }
            }
            level = LogLevel.ALL
        }
    }

    suspend fun getAIContentWithDetails(request: AIRequestWithDetails): AIResponse {
        return client.post {
            url(AI_API_URL)
            contentType(ContentType.Application.Json)
            setBody(
                mapOf(
                    "type" to request.type,
                    "format" to request.format,
                    "description" to request.description
                )
            )
        }.body()
    }

    suspend fun getAIContentWithDescription(request: AIRequestWithDescription): AIResponse {
        return client.post {
            url(AI_API_URL)
            contentType(ContentType.Application.Json)
            setBody(
                mapOf(
                    "contents" to listOf(
                        mapOf(
                            "parts" to listOf(
                                mapOf("text" to request.description)
                            )
                        )
                    )
                )
            )
        }.body()
    }
}