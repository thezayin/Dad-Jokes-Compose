package com.thezayin.dadjokes.screens.ai.presentation.state

import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel

data class AiState(
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isWriting: Boolean = false,
    val writingProgress: String = "",
    val isWritingCompleted: Boolean = false,
    val generatedJokes: List<AIContentModel> = emptyList(),
    val nativeAd: NativeAd? = null
)
