package com.thezayin.dadjokes.screens.ai.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.dadjokes.core.framework.admob.domain.repository.RewardedAdManager
import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import com.thezayin.dadjokes.screens.ai.domain.usecase.GetAIContentWithDescriptionUseCae
import com.thezayin.dadjokes.screens.ai.presentation.actions.AiAction
import com.thezayin.dadjokes.screens.ai.presentation.state.AiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class AiViewModel(
    private val generateAiContent: GetAIContentWithDescriptionUseCae,
    val rewardedAdManager: RewardedAdManager,
    val remoteConfig: com.thezayin.dadjokes.core.framework.remote.RemoteConfig,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AiState())
    val uiState: StateFlow<AiState> = _uiState.asStateFlow()

    var thoughtDuration: Int = 0
        private set

    fun handleAction(event: AiAction) {
        when (event) {
            is AiAction.ErrorMessage -> _uiState.update { it.copy(errorMessage = event.error) }
            is AiAction.FinishWriting -> _uiState.update {
                it.copy(
                    isWritingCompleted = true, writingProgress = ""
                )
            }

            is AiAction.IsError -> _uiState.update { it.copy(isError = event.error) }
            is AiAction.IsLoading -> _uiState.update { it.copy(isLoading = event.loading) }
            is AiAction.JokesIdeas -> _uiState.update { it.copy(generatedJokes = event.jokesIdeas) }
            is AiAction.StartWriting -> _uiState.update {
                it.copy(
                    isWriting = true, writingProgress = ""
                )
            }

            is AiAction.UpdateWritingProgress -> _uiState.update { it.copy(writingProgress = event.progress) }
        }
    }

    fun isError(error: Boolean) {
        handleAction(AiAction.IsError(error))
    }

    private fun errorMessage(error: String) {
        handleAction(AiAction.ErrorMessage(error))
    }

    fun isLoading(loading: Boolean) {
        handleAction(AiAction.IsLoading(loading))
    }

    private fun jokeIdeas(giftIdeas: List<AIContentModel>) {
        handleAction(AiAction.JokesIdeas(giftIdeas))
    }

    private fun updateWritingProgress(progress: String) {
        handleAction(AiAction.UpdateWritingProgress(progress))
    }

    private fun finishWriting() {
        handleAction(AiAction.FinishWriting)
    }

    fun fetchIdeas(description: String) {
        viewModelScope.launch {
            handleAction(AiAction.StartWriting)
            val minThoughtTime = 10_000L
            val timeTaken = measureTimeMillis {
                val progressSteps = listOf(
                    "Thinking", "Collecting data", "Almost completed"
                )
                for (step in progressSteps) {
                    updateWritingProgress(step)
                    delay(2000)
                }

                generateAiContent(description).collect {
                    when (it) {
                        is Response.Success -> {
                            jokeIdeas(it.data)
                            isLoading(false)
                        }

                        is Response.Error -> {
                            isLoading(false)
                            isError(true)
                            errorMessage("Please check your internet connection and try again.")
                        }

                        is Response.Loading -> isLoading(true)
                    }
                }

            }
            val remainingTime = minThoughtTime - timeTaken
            if (remainingTime > 0) {
                delay(remainingTime)
            }
            thoughtDuration =
                ((timeTaken + if (remainingTime > 0) remainingTime else 0) / 1000).toInt()

            finishWriting()
        }
    }
}
