package com.thezayin.presentation.event

import com.thezayin.domain.model.Joke

/**
 * Events that represent UI actions or updates for the Joke screen.
 */
sealed interface HomeUiEvents {
    data object ShowLoading : HomeUiEvents
    data object HideLoading : HomeUiEvents
    data object ShowErrorDialog : HomeUiEvents
    data object HideErrorDialog : HomeUiEvents
    data class ErrorMessage(val error: String) : HomeUiEvents
    data class JokeSuccess(val joke: Joke) : HomeUiEvents
}