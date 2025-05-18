package com.thezayin.dadjokes.screens.onboarding

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.thezayin.dadjokes.core.analytics.events.AnalyticsEvent
import com.thezayin.dadjokes.screens.onboarding.action.OnboardingActions
import com.thezayin.dadjokes.screens.onboarding.components.OnboardingContent
import org.koin.compose.koinInject

@Composable
fun OnboardingScreen(
    navigateToHome: () -> Unit,
) {
    val vm: OnboardingViewModel = koinInject()
    val state = vm.state.collectAsState()
    val activity = LocalActivity.current as Activity
    val adManager = vm.adManager
    LaunchedEffect(Unit) {
        adManager.loadAd(
            activity = activity
        )
    }

    if (state.value.isOnboardingCompleted) {
        adManager.showAd(
            activity = activity, showAd = vm.remoteConfig.adOnOnboardingCompleted, onNext = {
                navigateToHome()
            })
        return
    }
    vm.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("OnboardingScreen"))
    OnboardingContent(
        showAd = vm.remoteConfig.adBannerOnboarding,
        onboardPages = state.value.pages,
        currentPage = state.value.currentPage,
        onNextClicked = {
            if (state.value.currentPage < state.value.pages.size - 1) {
                vm.onAction(OnboardingActions.NextPage)
            } else {
                vm.onAction(OnboardingActions.CompleteOnboarding)
            }
        })
}