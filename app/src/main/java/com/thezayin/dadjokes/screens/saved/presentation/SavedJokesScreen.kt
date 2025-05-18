package com.thezayin.dadjokes.screens.saved.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.thezayin.dadjokes.screens.saved.presentation.component.SavedJokesScreenContent
import org.koin.compose.koinInject


@Composable
fun SavedJokesScreen(
    navigateToPreview: () -> Unit = {},
    navigateBack: () -> Unit,
) {
    val vm: SaveViewModel = koinInject()
    val state = vm.state.collectAsState().value
    val activity = androidx.activity.compose.LocalActivity.current as Activity
    val adManager = vm.interstitialAdManager

    LaunchedEffect(Unit) {
        adManager.loadAd(activity)
    }

    SavedJokesScreenContent(state = state, navigateBack = navigateBack, onDeleteClick = {
        adManager.showAd(
            activity = activity, showAd = vm.remoteConfig.adSaveDeleteAll, onNext = {
                vm.deleteAllJokes()
            })
    }, showBanner = vm.remoteConfig.adSaveBanner, onClick = { id ->
        adManager.showAd(
            activity = activity, showAd = vm.remoteConfig.adSaveJokeClick, onNext = {
                vm.getJokeById(id)
                navigateToPreview()
            })
    })
}