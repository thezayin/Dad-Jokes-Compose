package com.thezayin.presentation.di

import com.thezayin.data.repository.JokeRepositoryImpl
import com.thezayin.data.service.JokeApiService
import com.thezayin.domain.repository.JokeRepository
import com.thezayin.domain.usecase.FetchRandomJokeUseCase
import com.thezayin.domain.usecase.FetchRandomJokeUseCaseImpl
import com.thezayin.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    singleOf(::JokeApiService)
    singleOf(::JokeRepositoryImpl) bind JokeRepository::class
    singleOf(::FetchRandomJokeUseCaseImpl) bind FetchRandomJokeUseCase::class
    viewModelOf(::HomeViewModel)
}