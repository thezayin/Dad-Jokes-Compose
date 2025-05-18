package com.thezayin.dadjokes.screens.home.presentation.state

import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel

data class HomeState(
    val isJokeLiked: Boolean = false,
    val isLoading: Boolean = false,
    val joke: JokesModel? = null,
    val showErrorDialog: Boolean = false,
    val errorMessage: String = "",
    val isNetworkAvailable: Boolean = true
)