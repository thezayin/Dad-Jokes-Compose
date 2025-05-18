package com.thezayin.dadjokes.screens.onboarding.action

sealed class OnboardingActions {
    data object NextPage : OnboardingActions()
    data object CompleteOnboarding : OnboardingActions()
    data class ShowError(val errorMessage: String) : OnboardingActions()
}