package com.thezayin.dadjokes.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.dadjokes.domain.remote.usecase.RemoteUseCase
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val apiCall: RemoteUseCase,
) :
    ViewModel() {

    private val _jokeText = MutableStateFlow(GetJokeState())
    val jokeText = _jokeText.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    init {
        getRandomJoke()
    }

    fun getRandomJoke() = viewModelScope.launch {
        apiCall().collect { response ->
            when (response) {
                is Response.Success -> {
                    _jokeText.update {
                        it.copy(
                            joke = mutableStateOf(response.data)
                        )
                    }
                    delay(1000L)
                    _isLoading.update {
                        it.copy(
                            isLoading = mutableStateOf(false)
                        )
                    }
                }

                is Response.Error -> {
                    _isLoading.update {
                        it.copy(
                            isLoading = mutableStateOf(false)
                        )
                    }
                }

                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(
                            isLoading = mutableStateOf(true)
                        )
                    }
                }
            }
        }
    }

    data class GetJokeState(
        val joke: MutableState<JokesModel> = mutableStateOf(JokesModel("", "", 0))
    )

    data class GetLoadingState(
        val isLoading: MutableState<Boolean> = mutableStateOf(false)
    )
}