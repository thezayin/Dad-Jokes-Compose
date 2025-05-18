package com.thezayin.dadjokes.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import com.thezayin.dadjokes.core.framework.admob.domain.repository.AppOpenAdManager
import com.thezayin.dadjokes.core.framework.pref.PrefManager
import com.thezayin.dadjokes.core.framework.remote.RemoteConfig
import com.thezayin.dadjokes.screens.splash.action.SplashActions
import com.thezayin.dadjokes.screens.splash.state.SplashState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    val remoteConfig: RemoteConfig,
    val analytics: Analytics,
    val admobManager: AppOpenAdManager,
    preferencesManager: PrefManager,
) : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state.asStateFlow()

    val isFirstTime = preferencesManager.isFirstTime.value

    init {
        sendEvent(SplashActions.LoadSplash)
    }

    fun sendEvent(event: SplashActions) {
        when (event) {
            SplashActions.LoadSplash -> handleLoadSplash()
            is SplashActions.ShouldShowBannerAd -> _state.update { it.copy(shouldShowSplashAd = remoteConfig.adBannerSplash) }
            is SplashActions.ShouldShowSplashAd -> _state.update { it.copy(shouldShowSplashAd = remoteConfig.adOnSplash) }
        }
    }

    private fun handleLoadSplash() {
        viewModelScope.launch {
            val totalTime = 10000L
            val interval = totalTime / _state.value.splashTexts.size

            for (i in _state.value.splashTexts.indices) {
                _state.update {
                    it.copy(
                        currentSplashText = it.splashTexts[i],
                        currentSplashIndex = i
                    )
                }
                delay(interval)
            }
        }
    }
}