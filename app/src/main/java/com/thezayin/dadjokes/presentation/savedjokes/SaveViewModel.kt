package com.thezayin.dadjokes.presentation.savedjokes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.dadjokes.domain.local.usecase.DeleteAllJokesUseCase
import com.thezayin.dadjokes.domain.local.usecase.DeleteJokeUseCase
import com.thezayin.dadjokes.domain.local.usecase.GetAllJokesUseCase
import com.thezayin.dadjokes.domain.local.usecase.GetJokeUseCase
import com.thezayin.dadjokes.domain.local.usecase.SaveJokeUseCase
import com.thezayin.dadjokes.domain.remote.model.JokesModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SaveViewModel(
    private val getAllJokesUseCase: GetAllJokesUseCase,
    private val deleteJokeUseCase: DeleteJokeUseCase,
    private val deleteAllJokesUseCase: DeleteAllJokesUseCase,
    private val saveJokeUseCase: SaveJokeUseCase,
    private val getJokeUseCase: GetJokeUseCase
) : ViewModel() {

    private val _getAllJokes = MutableStateFlow(GetAllJokeState())
    val getAllJokes = _getAllJokes.asStateFlow()

    private val _getJokeById = MutableStateFlow(GetJokeDataState())
    val getJokeById = _getJokeById.asStateFlow()

    private val _isLoading = MutableStateFlow(GetJokesLoadingState())
    val isLoading = _isLoading.asStateFlow()

    init {
        getAllJokes()
    }

    private fun getAllJokes() = viewModelScope.launch {
        try {
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(true)
                )
            }
            delay(2000L)
            getAllJokesUseCase().collect { jokes ->
                _getAllJokes.update {
                    it.copy(
                        joke = jokes
                    )
                }
            }
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(false)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteAllJokes() = viewModelScope.launch {
        try {
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(true)
                )
            }
            delay(2000L)
            deleteAllJokesUseCase().collect {
                getAllJokes()
            }
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(false)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteJokeById(id: String) = viewModelScope.launch {
        try {
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(true)
                )
            }
            delay(2000L)
            deleteJokeUseCase(id).collect {
                getAllJokes()
            }
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(false)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveJoke(jokesModel: JokesModel) = viewModelScope.launch {
        try {
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(true)
                )
            }
            delay(2000L)
            saveJokeUseCase(jokesModel).collect {
                getAllJokes()
            }
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(false)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getJokeById(id: String) = viewModelScope.launch {
        try {
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(true)
                )
            }
            delay(2000L)
            getJokeUseCase(id).collect { joke ->
                _getJokeById.update {
                    it.copy(
                        joke = joke
                    )
                }
            }
            _isLoading.update {
                it.copy(
                    isLoading = mutableStateOf(false)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    data class GetJokeDataState(
        var joke: JokesModel = JokesModel("", "", 0)
    )

    data class GetAllJokeState(
        var joke: List<JokesModel> = emptyList()
    )

    data class GetJokesLoadingState(
        val isLoading: MutableState<Boolean> = mutableStateOf(false)
    )
}