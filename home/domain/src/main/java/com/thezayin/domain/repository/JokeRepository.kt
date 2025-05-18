package com.thezayin.domain.repository

import com.thezayin.domain.model.Joke
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for retrieving jokes.
 */
interface JokeRepository {
    suspend fun fetchRandomJoke(): Flow<Response<Joke>>
}