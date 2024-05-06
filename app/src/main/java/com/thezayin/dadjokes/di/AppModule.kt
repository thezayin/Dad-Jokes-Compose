package com.thezayin.dadjokes.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.thezayin.dadjokes.data.remote.ApiService
import com.thezayin.dadjokes.data.repository.LocalRepositoryImpl
import com.thezayin.dadjokes.data.repository.RemoteRepositoryImpl
import com.thezayin.dadjokes.domain.local.repository.LocalRepository
import com.thezayin.dadjokes.domain.local.usecase.DeleteAllJokesUseCase
import com.thezayin.dadjokes.domain.local.usecase.DeleteAllJokesUseCaseImpl
import com.thezayin.dadjokes.domain.local.usecase.DeleteJokeUseCase
import com.thezayin.dadjokes.domain.local.usecase.DeleteJokeUseCaseImpl
import com.thezayin.dadjokes.domain.local.usecase.GetAllJokesUseCase
import com.thezayin.dadjokes.domain.local.usecase.GetAllJokesUseCaseImpl
import com.thezayin.dadjokes.domain.local.usecase.GetJokeUseCase
import com.thezayin.dadjokes.domain.local.usecase.GetJokeUseCaseImpl
import com.thezayin.dadjokes.domain.local.usecase.SaveJokeUseCase
import com.thezayin.dadjokes.domain.local.usecase.SaveJokeUseCaseImpl
import com.thezayin.dadjokes.domain.remote.repository.RemoteRepository
import com.thezayin.dadjokes.domain.remote.usecase.RemoteUseCase
import com.thezayin.dadjokes.domain.remote.usecase.RemoteUseCaseImpl
import com.thezayin.dadjokes.presentation.activity.MainViewModel
import com.thezayin.dadjokes.presentation.activity.appupdate.UpdateManager
import com.thezayin.dadjokes.presentation.home.HomeViewModel
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel
import com.thezayin.framework.remote.RemoteConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.P)
val appModule = module {
    single { provideDatabase(androidContext()) }
    single { UpdateManager(androidContext()) }
    singleOf(::RemoteConfig)
    singleOf(::ApiService)
    singleOf(::provideDao)

    factoryOf(::LocalRepositoryImpl) bind LocalRepository::class
    factoryOf(::RemoteRepositoryImpl) bind RemoteRepository::class
    factoryOf(::RemoteUseCaseImpl) bind RemoteUseCase::class
    factoryOf(::SaveJokeUseCaseImpl) bind SaveJokeUseCase::class
    factoryOf(::GetJokeUseCaseImpl) bind GetJokeUseCase::class
    factoryOf(::GetAllJokesUseCaseImpl) bind GetAllJokesUseCase::class
    factoryOf(::DeleteJokeUseCaseImpl) bind DeleteJokeUseCase::class
    factoryOf(::DeleteAllJokesUseCaseImpl) bind DeleteAllJokesUseCase::class

    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SaveViewModel)
}
