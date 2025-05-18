package com.thezayin.domain.model


/**
 * Core domain model representing a Joke.
 */
data class Joke(
    val id: String,
    val content: String,
    val statusCode: Int
)