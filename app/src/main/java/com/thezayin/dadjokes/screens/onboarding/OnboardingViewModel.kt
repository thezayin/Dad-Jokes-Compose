package com.thezayin.dadjokes.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import com.thezayin.dadjokes.core.framework.admob.domain.repository.RewardedAdManager
import com.thezayin.dadjokes.core.framework.pref.PrefManager
import com.thezayin.dadjokes.core.framework.remote.RemoteConfig
import com.thezayin.dadjokes.screens.onboarding.action.OnboardingActions
import com.thezayin.dadjokes.screens.onboarding.model.OnboardingPage
import com.thezayin.dadjokes.screens.onboarding.state.OnboardingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    val analytics: Analytics,
    val remoteConfig: RemoteConfig,
    val adManager: RewardedAdManager,
    private val preferencesManager: PrefManager,
) : ViewModel() {
    private val _state = MutableStateFlow(
        OnboardingState(
            pages = listOf(
                OnboardingPage(images = R.drawable.ob_s1),
                OnboardingPage(images = R.drawable.ob_s2),
                OnboardingPage(images = R.drawable.ob_s3),
            )
        )
    )
    val state: StateFlow<OnboardingState> = _state

    fun onAction(action: OnboardingActions) {
        viewModelScope.launch {
            when (action) {
                is OnboardingActions.NextPage -> {
                    _state.update { currentState ->
                        val newPage =
                            (currentState.currentPage + 1).coerceAtMost(currentState.pages.size - 1)
                        currentState.copy(currentPage = newPage)
                    }
                }

                is OnboardingActions.CompleteOnboarding -> {
                    _state.update { currentState ->
                        currentState.copy(isOnboardingCompleted = true)
                    }
                    preferencesManager.setOnboardingCompleted()
                }

                is OnboardingActions.ShowError -> {
                    _state.update { currentState ->
                        currentState.copy(error = action.errorMessage)
                    }
                }
            }
        }
    }
}