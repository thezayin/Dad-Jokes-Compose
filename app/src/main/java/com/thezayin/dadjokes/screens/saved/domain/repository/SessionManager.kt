package com.thezayin.dadjokes.screens.saved.domain.repository

import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import kotlinx.coroutines.flow.StateFlow

interface SessionManager {
    val joke: StateFlow<JokesModel?>
    fun saveJokeId(joke: JokesModel?)
    fun clearSessionManager()
}