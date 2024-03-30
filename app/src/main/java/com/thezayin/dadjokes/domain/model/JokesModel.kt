package com.thezayin.dadjokes.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class JokesModel(
    val id: String,
    val joke: String,
    val status: Int
)