// data/repository/JokeRepositoryImpl.kt
package com.thezayin.data.repository

import com.thezayin.data.service.JokeApiService
import com.thezayin.domain.model.Joke
import com.thezayin.domain.repository.JokeRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Repository implementation for fetching jokes from a remote data source.
 */
class JokeRepositoryImpl(
    private val apiService: JokeApiService
) : JokeRepository {

    override suspend fun fetchRandomJoke(): Flow<Response<Joke>> = flow {
        emit(Response.Loading)
        val networkJoke = apiService.fetchRandomJoke()

        // Map NetworkJoke to domain Joke
        val joke = Joke(
            id = networkJoke.id,
            content = networkJoke.content,
            statusCode = networkJoke.statusCode
        )
        emit(Response.Success(joke))
    }.catch { e ->
        emit(Response.Error(e.localizedMessage ?: "An error occurred"))
    }
}
