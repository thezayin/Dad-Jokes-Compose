package com.thezayin.dadjokes.screens.splash

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.thezayin.dadjokes.core.analytics.events.AnalyticsEvent
import com.thezayin.dadjokes.core.common.GlassComponent
import com.thezayin.dadjokes.screens.splash.component.SplashScreenContent
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun SplashScreen(
    navigateToOnboarding: () -> Unit = {}, navigateToHome: () -> Unit
) {
    val vm: SplashViewModel = koinInject()
    val activity = LocalActivity.current as Activity
    val adManager = vm.admobManager
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        adManager.loadAd(activity)
    }

    vm.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("SplashScreen"))
    GlassComponent()
    LaunchedEffect(Unit) {
        delay(7000)
        adManager.showAd(
            activity = activity,
            showAd = state.shouldShowSplashAd,
            adImpression = {},
            onNext = {
                if (vm.isFirstTime) {
                    navigateToOnboarding()
                } else {
                    navigateToHome()
                }
            })
    }


    SplashScreenContent(
        text = state.currentSplashText, showBannerAd = state.shouldShowBannerAd
    )
}