package com.thezayin.dadjokes.screens.splash.action

sealed class SplashActions {
    data object LoadSplash : SplashActions()
    data class ShouldShowSplashAd(val shouldShow: Boolean) : SplashActions()
    data class ShouldShowBannerAd(val shouldShow: Boolean) : SplashActions()
}
