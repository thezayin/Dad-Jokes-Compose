package com.thezayin.dadjokes.screens.home.data.service

import com.thezayin.dadjokes.core.framework.utils.Constants.BASE_URL
import com.thezayin.dadjokes.screens.home.data.model.NetworkJoke
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

class JokeApiService {
    companion object {
        private const val TIME_MILLS = 60000L
    }

    private val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    private val client = HttpClient(Android) {
        install(HttpTimeout) {
            socketTimeoutMillis =
                TIME_MILLS
            requestTimeoutMillis =
                TIME_MILLS
            connectTimeoutMillis =
                TIME_MILLS
        }

        install(ContentNegotiation) { json(json) }

        install(Logging) {
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    Timber.tag("Response").i(message)
                }
            }
            level = LogLevel.ALL
        }
    }

    suspend fun fetchRandomJoke(): NetworkJoke {
        return client.get {
            url(BASE_URL)
        }.body()
    }
}
