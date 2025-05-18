package com.thezayin.dadjokes.screens.saved.data.repository

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.saved.data.local.database.Database
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import com.thezayin.dadjokes.screens.saved.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class LocalRepositoryImpl(
    database: Database
) : LocalRepository {
    private val daoImpl = database.dao()

    override suspend fun getAllJokes(): Flow<List<JokesModel>> = flow {
        try {
            val list = daoImpl.getAllJokes()
            emit(list)
        } catch (e: Exception) {
            Timber.e("Error fetching jokes: $e")
            emit(emptyList())
        }
    }

    override suspend fun getJokeById(id: String): Flow<Response<JokesModel>> = flow {
        try {
            emit(Response.Loading)
            val response = daoImpl?.getJokeById(id)
            if (response == null) {
                emit(Response.Error("Joke not found"))
                return@flow
            }
            emit(Response.Success(response))
        } catch (e: Exception) {
            Timber.e("Error fetching joke by ID: $e")
            emit(Response.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }

    override suspend fun saveJoke(jokesModel: JokesModel): Flow<Boolean> = flow {
        try {
            daoImpl.saveJoke(jokesModel)
            emit(true)
        } catch (e: Exception) {
            Timber.e("Error saving joke: $e")
            emit(false)
        }
    }

    override suspend fun deleteJokeById(id: String): Flow<Boolean> = flow {
        try {
            daoImpl.deleteJokeById(id)
            emit(true)
        } catch (e: Exception) {
            Timber.e("Error deleting joke by ID: $e")
            emit(false)
        }
    }

    override suspend fun deleteAllJokes(): Flow<Boolean> = flow {
        try {
            daoImpl.deleteAllJokes()
            emit(true)
        } catch (e: Exception) {
            Timber.e("Error deleting all jokes: $e")
            emit(false)
        }
    }
}