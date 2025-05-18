package com.thezayin.dadjokes.screens.home.presentation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.thezayin.dadjokes.screens.home.presentation.components.HomeScreenContent
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    onNavigateToSettings: () -> Unit = {},
    onNavigateToSavedJokes: () -> Unit = {},
    onNavigateToAiScreen: () -> Unit = {},
) {
    val vm: HomeViewModel = koinInject()
    val state = vm.homeState.collectAsState().value
    val activity = LocalActivity.current as Activity

    val interstitialAd = vm.interstitialAdManager
    val rewardedAd = vm.rewardedAdManager

    LaunchedEffect(Unit) {
        vm.initManager(activity)
    }

    HomeScreenContent(state = state, nextJoke = {
        vm.onNextJokeClick(activity)
    }, showBottomAd = vm.remoteConfig.adBannerHome, navigateToAiScreen = {
        interstitialAd.showAd(
            activity = activity, showAd = vm.remoteConfig.adInterstitialHomeAi, onNext = {
                onNavigateToAiScreen()
            })
    }, navigateToSettingScreen = onNavigateToSettings, navigateToSavedJokesScreen = {
        rewardedAd.showAd(
            activity = activity, showAd = vm.remoteConfig.adRewardedHomeLiked, onNext = {
                onNavigateToSavedJokes()
            })
    }, saveCurrentJoke = {
        vm.saveJoke()
    }, removeCurrentJoke = {
        vm.removeJoke()
    })
}