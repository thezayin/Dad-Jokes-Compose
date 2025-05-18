package com.thezayin.dadjokes.screens.saved.presentation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.saved.domain.repository.SessionManager
import com.thezayin.dadjokes.screens.saved.domain.usecase.DeleteAllJokesUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.DeleteJokeUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.GetAllJokesUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.GetJokeUseCase
import com.thezayin.dadjokes.screens.saved.presentation.actions.SaveJokeActions
import com.thezayin.dadjokes.screens.saved.presentation.state.SaveJokeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class SaveViewModel(
    val application: Application,
    private val sessionManager: SessionManager,
    private val getAllJokesUseCase: GetAllJokesUseCase,
    private val deleteJokeUseCase: DeleteJokeUseCase,
    private val deleteAllJokesUseCase: DeleteAllJokesUseCase,
    internal val remoteConfig: com.thezayin.dadjokes.core.framework.remote.RemoteConfig,
    private val getJokeUseCase: GetJokeUseCase,
    val interstitialAdManager: com.thezayin.dadjokes.core.framework.admob.domain.repository.InterstitialAdManager,
    val rewardedAdManager: com.thezayin.dadjokes.core.framework.admob.domain.repository.RewardedAdManager,
) : ViewModel() {

    private val _state = MutableStateFlow(SaveJokeState())
    val state: StateFlow<SaveJokeState> = _state.asStateFlow()

    init {
        loadAllJokes()
        fetchFromSessionManager()
    }

    fun handleAction(event: SaveJokeActions) {
        when (event) {
            is SaveJokeActions.Error -> _state.update { it.copy(errorMessage = event.message) }
            is SaveJokeActions.JokeDeleted -> _state.update { it.copy(isDeleted = event.success) }
            is SaveJokeActions.LoadJokes -> _state.update { it.copy(jokes = event.jokes) }
            is SaveJokeActions.ShowLoading -> _state.update { it.copy(isLoading = event.showLoading) }
            is SaveJokeActions.AllJokesDeleted -> _state.update { it.copy(allJokesDeleted = event.success) }
            is SaveJokeActions.ShowJokeById -> _state.update { it.copy(jokeById = event.joke) }
            is SaveJokeActions.Joke -> _state.update { it.copy(joke = event.joke) }
        }
    }

    private fun loadAllJokes() = viewModelScope.launch {
        try {
            handleAction(SaveJokeActions.ShowLoading(true))
            getAllJokesUseCase().collect { jokes ->
                handleAction(SaveJokeActions.LoadJokes(jokes))
            }
            delay(4000L)
            handleAction(SaveJokeActions.ShowLoading(false))
        } catch (e: Exception) {
            Timber.e("Error loading jokes: $e")
            handleAction(SaveJokeActions.ShowLoading(false))
            handleAction(SaveJokeActions.Error("Failed to load jokes: ${e.localizedMessage}"))
        }
    }

    fun fetchFromSessionManager() = viewModelScope.launch {
        sessionManager.joke.collect { joke ->
            if (joke != null) {
                handleAction(SaveJokeActions.Joke(joke))
            }
        }
    }

    fun deleteJokeById(id: String) = viewModelScope.launch {
        try {
            handleAction(SaveJokeActions.ShowLoading(true))
            deleteJokeUseCase(id).collect { success ->
                if (success) {
                    loadAllJokes()
                    handleAction(SaveJokeActions.JokeDeleted(true))
                    Toast.makeText(
                        application, "Joke deleted successfully", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        application, "Failed to delete joke", Toast.LENGTH_SHORT
                    ).show()
                    handleAction(SaveJokeActions.Error("Failed to delete joke"))
                }
            }

        } catch (e: Exception) {
            Timber.e("Error deleting joke: $e")
            handleAction(SaveJokeActions.ShowLoading(false))
            handleAction(SaveJokeActions.Error("Error occurred: ${e.localizedMessage}"))
        }
    }


    fun getJokeById(id: String) = viewModelScope.launch {
        try {
            handleAction(SaveJokeActions.ShowLoading(true))
            getJokeUseCase(id).collect { jokeResponse ->
                when (jokeResponse) {
                    is Response.Success -> {
                        handleAction(SaveJokeActions.ShowJokeById(jokeResponse.data))
                        sessionManager.saveJokeId(jokeResponse.data)
                        handleAction(SaveJokeActions.ShowLoading(false))
                    }

                    is Response.Error -> {
                        handleAction(SaveJokeActions.ShowLoading(false))
                        handleAction(SaveJokeActions.Error("Error occurred: ${jokeResponse.e}"))
                    }

                    is Response.Loading -> {
                        handleAction(SaveJokeActions.ShowLoading(true))
                    }
                }
            }
        } catch (e: Exception) {
            Timber.e("Error fetching joke by ID: $e")
            handleAction(SaveJokeActions.ShowLoading(false))
            handleAction(SaveJokeActions.Error("Error occurred: ${e.localizedMessage}"))
        }
    }

    fun deleteAllJokes() = viewModelScope.launch {
        try {
            handleAction(SaveJokeActions.ShowLoading(true))
            deleteAllJokesUseCase().collect { success ->
                if (success) {
                    loadAllJokes()
                    handleAction(SaveJokeActions.AllJokesDeleted(true))
                } else {
                    handleAction(SaveJokeActions.Error("Failed to delete all jokes"))
                }
                handleAction(SaveJokeActions.ShowLoading(false))
            }
        } catch (e: Exception) {
            Timber.e("Error deleting all jokes: $e")
            handleAction(SaveJokeActions.ShowLoading(false))
            handleAction(SaveJokeActions.Error("Error occurred: ${e.localizedMessage}"))
        }
    }
}