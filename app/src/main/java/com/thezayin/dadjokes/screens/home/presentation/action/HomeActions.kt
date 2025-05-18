package com.thezayin.dadjokes.screens.home.presentation.action

import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel

sealed class HomeActions {
    data object ShowLoading : HomeActions()
    data object HideLoading : HomeActions()
    data object ShowErrorDialog : HomeActions()
    data object HideErrorDialog : HomeActions()
    data class ErrorMessage(val error: String) : HomeActions()
    data class JokeSuccess(val joke: JokesModel) : HomeActions()
    data class JokeLiked(val isLiked: Boolean) : HomeActions()
    data class NetworkStatus(val isConnected: Boolean) : HomeActions()
}