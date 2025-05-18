package com.thezayin.dadjokes.screens.saved.presentation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.thezayin.dadjokes.core.common.GlassComponent
import com.thezayin.dadjokes.screens.saved.presentation.component.PreviewJokeScreenContent
import org.koin.compose.koinInject

@Composable
fun PreviewJokeScreen(
    onNavigateBack: () -> Unit
) {
    val vm: SaveViewModel = koinInject()
    val state = vm.state.collectAsState().value
    val adManager = vm.rewardedAdManager
    val activity = LocalActivity.current as Activity

    LaunchedEffect(Unit) {
        adManager.loadAd(activity)
    }

    GlassComponent()
    PreviewJokeScreenContent(
        state = state, showBottomAd = vm.remoteConfig.adBannerPreview, onDeleteClick = {
            adManager.showAd(
                activity = activity, showAd = vm.remoteConfig.adPreviewJokeDelete, onNext = {
                    vm.deleteJokeById(state.joke?.id ?: "")
                })
            vm.deleteJokeById(state.joke?.id ?: "")
            onNavigateBack()
        }, navigateBack = onNavigateBack
    )
}