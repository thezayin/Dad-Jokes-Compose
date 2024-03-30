package com.thezayin.dadjokes.presentation.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.dadjokes.domain.usecase.RemoteUseCase
import com.thezayin.dadjokes.presentation.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val apiCall: RemoteUseCase) : ViewModel() {

   var jokeText by mutableStateOf(GetJokeState())
        private set

    var isLoadingState by mutableStateOf(GetLoadingState())
        private set

    init {
        getRandomJoke()
    }

    fun getRandomJoke() = viewModelScope.launch {
        apiCall().collect { response ->
            when (response) {
                is Response.Success -> {
                    jokeText.joke.value = response.data.joke
                    delay(250L)
                    isLoadingState.isLoading.value = false
                }

                is Response.Error -> {
                    isLoadingState.isLoading.value = false
                }

                is Response.Loading -> {
                    isLoadingState.isLoading.value = true
                }
            }
        }
    }

    data class GetJokeState(
        val joke: MutableState<String> = mutableStateOf("")
    )

    data class GetLoadingState(
        val isLoading: MutableState<Boolean> = mutableStateOf(false)
    )
}