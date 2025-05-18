package com.thezayin.dadjokes.screens.home.presentation

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.dadjokes.core.framework.remote.RemoteConfig
import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.home.domain.usecase.FetchRandomJokeUseCase
import com.thezayin.dadjokes.screens.home.presentation.action.HomeActions
import com.thezayin.dadjokes.screens.home.presentation.action.HomeActions.JokeLiked
import com.thezayin.dadjokes.screens.home.presentation.state.HomeState
import com.thezayin.dadjokes.screens.saved.domain.usecase.DeleteJokeUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.GetJokeUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.SaveJokeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val application: Application,
    private val saveJokeUseCase: SaveJokeUseCase,
    private val fetchRandomJokeUseCase: FetchRandomJokeUseCase,
    private val deleteJoke: DeleteJokeUseCase,
    private val fetchSaveJoke: GetJokeUseCase,
    val remoteConfig: RemoteConfig,
    val rewardedAdManager: com.thezayin.dadjokes.core.framework.admob.domain.repository.RewardedAdManager,
    val interstitialAdManager: com.thezayin.dadjokes.core.framework.admob.domain.repository.InterstitialAdManager,
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    private var clickCounter = 0

    init {
        fetchJokes()
    }

    fun initManager(activity: Activity) {
        rewardedAdManager.loadAd(activity)
        interstitialAdManager.loadAd(activity)
    }

    private fun handleEvent(event: HomeActions) {
        when (event) {
            is JokeLiked -> _homeState.update { it.copy(isJokeLiked = event.isLiked) }
            is HomeActions.ShowLoading -> _homeState.update { it.copy(isLoading = true) }
            is HomeActions.HideLoading -> _homeState.update { it.copy(isLoading = false) }
            is HomeActions.ShowErrorDialog -> _homeState.update { it.copy(showErrorDialog = true) }
            is HomeActions.HideErrorDialog -> _homeState.update { it.copy(showErrorDialog = false) }
            is HomeActions.ErrorMessage -> _homeState.update { it.copy(errorMessage = event.error) }
            is HomeActions.JokeSuccess -> _homeState.update { it.copy(joke = event.joke) }
            is HomeActions.NetworkStatus -> _homeState.update { it.copy(isNetworkAvailable = event.isConnected) }
        }
    }

    fun fetchJokes() = viewModelScope.launch {
        val connected = isNetworkConnected()
        handleEvent(HomeActions.NetworkStatus(connected))
        if (!connected) {
            handleEvent(HomeActions.HideLoading)
            handleEvent(HomeActions.ShowErrorDialog)
            handleEvent(HomeActions.ErrorMessage("No internet connection"))
            return@launch
        }

        handleEvent(HomeActions.ShowLoading)
        fetchRandomJokeUseCase()
            .catch { e ->
                handleEvent(HomeActions.HideLoading)
                handleEvent(HomeActions.ShowErrorDialog)
                handleEvent(HomeActions.ErrorMessage(e.localizedMessage ?: "An error occurred"))
            }
            .collect { response ->
                when (response) {
                    is Response.Loading -> handleEvent(HomeActions.ShowLoading)
                    is Response.Error -> {
                        handleEvent(HomeActions.HideLoading)
                        handleEvent(HomeActions.ShowErrorDialog)
                        handleEvent(HomeActions.ErrorMessage(response.e))
                    }

                    is Response.Success -> {
                        handleEvent(HomeActions.JokeSuccess(response.data))
                        checkIfJokeIsLiked(response.data.id)
                        delay(2_000L)
                        handleEvent(HomeActions.HideLoading)
                    }
                }
            }
    }

    fun onNextJokeClick(activity: Activity) {
        clickCounter++
        if (clickCounter == 4) {
            interstitialAdManager.showAd(
                activity = activity,
                showAd = remoteConfig.adInterstitialHomeNext,
                onNext = {
                    clickCounter = 0
                    fetchJokes()
                }
            )
        } else {
            fetchJokes()
        }
    }

    fun saveJoke() = viewModelScope.launch {
        val joke = homeState.value.joke
        if (joke != null) {
            saveJokeUseCase(joke)
                .catch { e ->
                    handleEvent(HomeActions.ShowErrorDialog)
                    handleEvent(HomeActions.ErrorMessage(e.localizedMessage ?: "An error occurred"))
                }
                .collect { response ->
                    when (response) {
                        true -> {
                            Toast.makeText(
                                application,
                                "Joke saved successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            checkIfJokeIsLiked(joke.id)
                        }

                        false -> {
                            Toast.makeText(
                                application,
                                "Failed to save joke",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }

    fun removeJoke() = viewModelScope.launch {
        val joke = homeState.value.joke
        if (joke != null) {
            deleteJoke(joke.id)
                .catch { e ->
                    handleEvent(HomeActions.ShowErrorDialog)
                    handleEvent(HomeActions.ErrorMessage(e.localizedMessage ?: "An error occurred"))
                }
                .collect { success ->
                    if (success) {
                        handleEvent(JokeLiked(false))
                        Toast.makeText(
                            application,
                            "Joke removed successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    fun checkIfJokeIsLiked(jokeId: String) = viewModelScope.launch {
        fetchSaveJoke(jokeId).collect { response ->
            when (response) {
                is Response.Success -> {
                    handleEvent(JokeLiked(true))
                }

                is Response.Error -> {
                    handleEvent(JokeLiked(false))
                }

                Response.Loading -> Unit
            }
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = application
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val cap = cm.activeNetwork
            ?.let { cm.getNetworkCapabilities(it) }
        return cap?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}