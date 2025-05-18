package com.thezayin.dadjokes.screens.home.domain.usecase

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.home.domain.repository.JokeRepository
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import kotlinx.coroutines.flow.Flow

interface FetchRandomJokeUseCase : suspend () -> Flow<Response<JokesModel>>

class FetchRandomJokeUseCaseImpl(
    private val repository: JokeRepository
) : FetchRandomJokeUseCase {
    override suspend operator fun invoke(): Flow<Response<JokesModel>> =
        repository.fetchRandomJoke()
}