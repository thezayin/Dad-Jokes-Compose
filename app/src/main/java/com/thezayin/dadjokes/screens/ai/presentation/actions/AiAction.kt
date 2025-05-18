package com.thezayin.dadjokes.screens.ai.presentation.actions

import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel

sealed class AiAction {
    data class IsError(val error: Boolean) : AiAction()
    data class ErrorMessage(val error: String) : AiAction()
    data class IsLoading(val loading: Boolean) : AiAction()
    data class JokesIdeas(val jokesIdeas: List<AIContentModel>) : AiAction()
    data object StartWriting : AiAction()
    data class UpdateWritingProgress(val progress: String) : AiAction()
    data object FinishWriting : AiAction()
}