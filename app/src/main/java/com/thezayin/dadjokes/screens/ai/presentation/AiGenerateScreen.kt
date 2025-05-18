package com.thezayin.dadjokes.screens.ai.presentation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.thezayin.dadjokes.core.common.GlassComponent
import com.thezayin.dadjokes.core.framework.extension.functions.copyText
import com.thezayin.dadjokes.core.framework.extension.functions.share
import com.thezayin.dadjokes.screens.ai.presentation.component.JokeIdeasScreenContent
import org.koin.compose.koinInject

@Composable
fun AiGenerateScreen(
    navigateBack: () -> Unit = {},
) {
    val vm: AiViewModel = koinInject()
    val state by vm.uiState.collectAsState()

    val context = LocalContext.current
    val activity = LocalActivity.current as Activity

    val rewardedAd = vm.rewardedAdManager
    LaunchedEffect(Unit) {
        rewardedAd.loadAd(activity)
    }
    GlassComponent()
    JokeIdeasScreenContent(
        showAd = vm.remoteConfig.adRewardedAiBanner,
        showError = state.isError,
        error = state.errorMessage,
        navigateBack = navigateBack,
        isWriting = state.isWriting,
        writingProgress = state.writingProgress,
        isWritingCompleted = state.isWritingCompleted,
        jokeIdeas = state.generatedJokes,
        onGenerateClick = { description ->
            rewardedAd.showAd(
                activity = activity, showAd = vm.remoteConfig.adRewardedAiGenerate, onNext = {
                    vm.fetchIdeas(description)
                })
        },
        onCopyClick = {
            val fullText =
                state.generatedJokes.joinToString(separator = "\n\n") { "${it.title}\n${it.description}" }
            context.copyText(fullText)
        },
        onShareClick = {
            val fullText =
                state.generatedJokes.joinToString(separator = "\n\n") { "${it.title}\n${it.description}" }
            context.share(fullText)
        })
}