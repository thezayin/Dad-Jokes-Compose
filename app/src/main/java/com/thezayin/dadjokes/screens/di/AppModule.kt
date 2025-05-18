@file:Suppress("DEPRECATION")

package com.thezayin.dadjokes.screens.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import com.thezayin.dadjokes.core.analytics.analytics.AnalyticsImpl
import com.thezayin.dadjokes.core.framework.admob.data.repository.AppOpenAdManagerImpl
import com.thezayin.dadjokes.core.framework.admob.data.repository.InterstitialAdManagerImpl
import com.thezayin.dadjokes.core.framework.admob.data.repository.RewardedAdManagerImpl
import com.thezayin.dadjokes.core.framework.admob.domain.repository.AppOpenAdManager
import com.thezayin.dadjokes.core.framework.admob.domain.repository.InterstitialAdManager
import com.thezayin.dadjokes.core.framework.admob.domain.repository.RewardedAdManager
import com.thezayin.dadjokes.core.framework.pref.PrefManager
import com.thezayin.dadjokes.core.framework.remote.RemoteConfig
import com.thezayin.dadjokes.screens.ai.data.remote.AIService
import com.thezayin.dadjokes.screens.ai.data.repository.AIContentRepositoryImpl
import com.thezayin.dadjokes.screens.ai.domain.repository.AIContentRepository
import com.thezayin.dadjokes.screens.ai.domain.usecase.GetAIContentWithDescriptionUseCae
import com.thezayin.dadjokes.screens.ai.domain.usecase.GetAIContentWithDescriptionUseCaseImpl
import com.thezayin.dadjokes.screens.ai.domain.usecase.GetGeneratedAIWithDetailsUseCase
import com.thezayin.dadjokes.screens.ai.domain.usecase.GetGeneratedAIWithDetailsUseCaseImpl
import com.thezayin.dadjokes.screens.ai.presentation.AiViewModel
import com.thezayin.dadjokes.screens.home.data.repository.JokeRepositoryImpl
import com.thezayin.dadjokes.screens.home.data.service.JokeApiService
import com.thezayin.dadjokes.screens.home.domain.repository.JokeRepository
import com.thezayin.dadjokes.screens.home.domain.usecase.FetchRandomJokeUseCase
import com.thezayin.dadjokes.screens.home.domain.usecase.FetchRandomJokeUseCaseImpl
import com.thezayin.dadjokes.screens.home.presentation.HomeViewModel
import com.thezayin.dadjokes.screens.onboarding.OnboardingViewModel
import com.thezayin.dadjokes.screens.saved.data.repository.LocalRepositoryImpl
import com.thezayin.dadjokes.screens.saved.data.repository.SessionManagerImpl
import com.thezayin.dadjokes.screens.saved.domain.repository.LocalRepository
import com.thezayin.dadjokes.screens.saved.domain.repository.SessionManager
import com.thezayin.dadjokes.screens.saved.domain.usecase.DeleteAllJokesUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.DeleteAllJokesUseCaseImpl
import com.thezayin.dadjokes.screens.saved.domain.usecase.DeleteJokeUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.DeleteJokeUseCaseImpl
import com.thezayin.dadjokes.screens.saved.domain.usecase.GetAllJokesUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.GetAllJokesUseCaseImpl
import com.thezayin.dadjokes.screens.saved.domain.usecase.GetJokeUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.GetJokeUseCaseImpl
import com.thezayin.dadjokes.screens.saved.domain.usecase.SaveJokeUseCase
import com.thezayin.dadjokes.screens.saved.domain.usecase.SaveJokeUseCaseImpl
import com.thezayin.dadjokes.screens.saved.presentation.SaveViewModel
import com.thezayin.dadjokes.screens.setting.SettingViewModel
import com.thezayin.dadjokes.screens.splash.SplashViewModel
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.P)
val appModule = module {
    single { provideDatabase(androidContext()) }
    singleOf(::provideDao)
}

val aiModule = module {
    single { AIService() }
    single<AIContentRepository> { AIContentRepositoryImpl(get()) }
    single<GetAIContentWithDescriptionUseCae> { GetAIContentWithDescriptionUseCaseImpl(get()) }
    single<GetGeneratedAIWithDetailsUseCase> { GetGeneratedAIWithDetailsUseCaseImpl(get()) }
    viewModelOf(::AiViewModel)
}

val frameworkModule = module {
    singleOf(::PrefManager)
    singleOf(::RemoteConfig)
    single { FirebaseAnalytics.getInstance(get()) }
    single { Json { ignoreUnknownKeys = true } }
    singleOf(::AppOpenAdManagerImpl) bind AppOpenAdManager::class
    singleOf(::RewardedAdManagerImpl) bind RewardedAdManager::class
    singleOf(::InterstitialAdManagerImpl) bind InterstitialAdManager::class

}
val splashModule = module {
    viewModelOf(::SplashViewModel)
}

val settingModule = module {
    viewModelOf(::SettingViewModel)
}

val onBoardingModule = module {
    viewModelOf(::OnboardingViewModel)
}

val savedModule = module {
    factoryOf(::SessionManagerImpl) bind SessionManager::class
    factoryOf(::LocalRepositoryImpl) bind LocalRepository::class
    factoryOf(::SaveJokeUseCaseImpl) bind SaveJokeUseCase::class
    factoryOf(::GetJokeUseCaseImpl) bind GetJokeUseCase::class
    factoryOf(::GetAllJokesUseCaseImpl) bind GetAllJokesUseCase::class
    factoryOf(::DeleteJokeUseCaseImpl) bind DeleteJokeUseCase::class
    factoryOf(::DeleteAllJokesUseCaseImpl) bind DeleteAllJokesUseCase::class
    viewModelOf(::SaveViewModel)
}

val analyticsModule = module {
    single { FirebaseAnalytics.getInstance(get()) }
    factoryOf(::AnalyticsImpl) bind Analytics::class
}

val homeModule = module {
    singleOf(::JokeApiService)
    singleOf(::JokeRepositoryImpl) bind JokeRepository::class
    singleOf(::FetchRandomJokeUseCaseImpl) bind FetchRandomJokeUseCase::class
    viewModelOf(::HomeViewModel)
}