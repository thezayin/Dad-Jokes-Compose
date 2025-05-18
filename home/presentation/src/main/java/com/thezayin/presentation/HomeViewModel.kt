package com.thezayin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.domain.model.Joke
import com.thezayin.domain.usecase.FetchRandomJokeUseCase
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.event.HomeUiEvents
import com.thezayin.presentation.state.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchRandomJokeUseCase: FetchRandomJokeUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        fetchJokes()
    }

    private fun handleEvent(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.ShowLoading -> _homeState.update { it.copy(isLoading = true) }
            is HomeUiEvents.HideLoading -> _homeState.update { it.copy(isLoading = false) }
            is HomeUiEvents.ShowErrorDialog -> _homeState.update { it.copy(showErrorDialog = true) }
            is HomeUiEvents.HideErrorDialog -> _homeState.update { it.copy(showErrorDialog = false) }
            is HomeUiEvents.ErrorMessage -> _homeState.update { it.copy(errorMessage = event.error) }
            is HomeUiEvents.JokeSuccess -> _homeState.update { it.copy(joke = event.joke) }
        }
    }

    fun fetchJokes() = viewModelScope.launch {
        showLoading()
        fetchRandomJokeUseCase.invoke().catch { exception ->
            hideLoading()
            showErrorDialog()
            errorMessage(exception.message ?: "An error occurred")
        }.collect { response ->
            when (response) {
                is Response.Loading -> showLoading()
                is Response.Error -> {
                    hideLoading()
                    showErrorDialog()
                    errorMessage(response.e)
                }

                is Response.Success -> {
                    jokeSuccess(response.data)
                    delay(2000L)
                    hideLoading()
                }
            }
        }
    }

    fun jokeSuccess(joke: Joke) {
        handleEvent(HomeUiEvents.JokeSuccess(joke))
    }

    fun dismissErrorDialog() {
        handleEvent(HomeUiEvents.HideErrorDialog)
    }

    fun showLoading() {
        handleEvent(HomeUiEvents.ShowLoading)
    }

    fun hideLoading() {
        handleEvent(HomeUiEvents.HideLoading)
    }

    fun showErrorDialog() {
        handleEvent(HomeUiEvents.ShowErrorDialog)
    }

    fun errorMessage(error: String) {
        handleEvent(HomeUiEvents.ErrorMessage(error))
    }
}