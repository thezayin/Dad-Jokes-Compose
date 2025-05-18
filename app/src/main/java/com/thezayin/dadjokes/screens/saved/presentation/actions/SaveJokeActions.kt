package com.thezayin.dadjokes.screens.saved.presentation.actions

import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel

sealed class SaveJokeActions {
    data class ShowLoading(val showLoading: Boolean) : SaveJokeActions()
    data class LoadJokes(val jokes: List<JokesModel>) : SaveJokeActions()
    data class JokeDeleted(val success: Boolean) : SaveJokeActions()
    data class Error(val message: String) : SaveJokeActions()
    data class AllJokesDeleted(val success: Boolean) : SaveJokeActions()
    data class ShowJokeById(val joke: JokesModel) : SaveJokeActions()
    data class Joke(val joke: JokesModel?) : SaveJokeActions()
}
