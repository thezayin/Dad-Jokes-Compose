package com.thezayin.dadjokes.data.repository


import com.thezayin.dadjokes.data.remote.ApiService
import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.dadjokes.domain.remote.repository.RemoteRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRepositoryImpl(
    private val apiService: ApiService
) : RemoteRepository {
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