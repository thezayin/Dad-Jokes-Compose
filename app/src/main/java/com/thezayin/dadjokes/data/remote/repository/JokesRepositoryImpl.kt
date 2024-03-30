package com.thezayin.dadjokes.data.remote.repository


import com.thezayin.dadjokes.data.remote.network.ApiService
import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.dadjokes.domain.repository.JokesRepository
import com.thezayin.dadjokes.presentation.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokesRepositoryImpl(
    private val apiService: ApiService
) : JokesRepository {
    override suspend fun getRandomJoke(): Flow<Response<JokesModel>> = flow {
        try {
            emit(Response.Loading)
            val response = apiService.getJokes()
            emit(Response.Success(response))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}