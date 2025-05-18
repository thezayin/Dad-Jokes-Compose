package com.thezayin.splash.di

import com.thezayin.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Koin module for providing dependencies related to the splash screen.
 * It registers the [SplashViewModel] to be injected wherever required.
 */
val splashModule = module {
    // Provides the SplashViewModel using Koin's viewModelOf function
    viewModelOf(::SplashViewModel)
}
