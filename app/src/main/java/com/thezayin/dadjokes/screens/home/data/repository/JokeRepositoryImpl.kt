package com.thezayin.dadjokes.screens.home.data.repository

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.home.data.service.JokeApiService
import com.thezayin.dadjokes.screens.home.domain.repository.JokeRepository
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class JokeRepositoryImpl(
    private val apiService: JokeApiService
) : JokeRepository {

    override suspend fun fetchRandomJoke(): Flow<Response<JokesModel>> = flow {
        emit(Response.Loading)
        val networkJoke = apiService.fetchRandomJoke()
        val joke = JokesModel(
            id = networkJoke.id, joke = networkJoke.content, status = networkJoke.statusCode
        )
        emit(Response.Success(joke))
    }.catch { e ->
        Timber.e("Error fetching joke: $e")
        emit(Response.Error(e.localizedMessage ?: "An error occurred"))
    }
}
