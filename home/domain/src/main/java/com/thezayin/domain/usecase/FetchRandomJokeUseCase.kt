package com.thezayin.domain.usecase

import com.thezayin.domain.model.Joke
import com.thezayin.domain.repository.JokeRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface FetchRandomJokeUseCase : suspend () -> Flow<Response<Joke>>

/**
 * Use case for fetching a random joke from the repository.
 */
class FetchRandomJokeUseCaseImpl(
    private val repository: JokeRepository
) : FetchRandomJokeUseCase {
    override suspend operator fun invoke(): Flow<Response<Joke>> = repository.fetchRandomJoke()
}