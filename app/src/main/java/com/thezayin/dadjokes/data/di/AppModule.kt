package com.thezayin.dadjokes.data.di

import com.thezayin.dadjokes.data.remote.network.ApiService
import com.thezayin.dadjokes.data.remote.repository.JokesRepositoryImpl
import com.thezayin.dadjokes.domain.repository.JokesRepository
import com.thezayin.dadjokes.domain.usecase.RemoteUseCase
import com.thezayin.dadjokes.domain.usecase.RemoteUseCaseImpl
import com.thezayin.dadjokes.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::ApiService)
    factoryOf(::JokesRepositoryImpl) bind JokesRepository::class
    factoryOf(::RemoteUseCaseImpl) bind RemoteUseCase::class
    viewModelOf(::HomeViewModel)
}