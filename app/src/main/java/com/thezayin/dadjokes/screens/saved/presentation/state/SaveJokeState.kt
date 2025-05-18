package com.thezayin.dadjokes.screens.saved.presentation.state

import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel

data class SaveJokeState(
    val isLoading: Boolean = false,
    val jokes: List<JokesModel>? = emptyList(),
    val errorMessage: String? = null,
    val isDeleted: Boolean = false,
    val allJokesDeleted: Boolean = false,
    val joke: JokesModel? = null,
    val jokeById: JokesModel? = null,
)
