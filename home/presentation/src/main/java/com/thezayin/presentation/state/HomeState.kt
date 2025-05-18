package com.thezayin.presentation.state

import com.thezayin.domain.model.Joke

/**
 * State representing the UI state of the Joke screen.
 */
data class HomeState(
    val isLoading: Boolean = false,
    val joke: Joke? = null,
    val showErrorDialog: Boolean = false,
    val errorMessage: String = ""
)