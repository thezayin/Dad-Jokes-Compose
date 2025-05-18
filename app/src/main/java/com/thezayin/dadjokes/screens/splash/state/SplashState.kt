package com.thezayin.dadjokes.screens.splash.state

import com.thezayin.dadjokes.R

data class SplashState(
    val isLoading: Boolean = true,
    val shouldShowBannerAd: Boolean = true,
    val shouldShowSplashAd: Boolean = true,
    val currentSplashText: Int = R.string.loading_settings,
    val splashTexts: List<Int> = listOf(
        R.string.loading_settings, R.string.finishing_up, R.string.almost_there
    ),
    val currentSplashIndex: Int = 0,
)