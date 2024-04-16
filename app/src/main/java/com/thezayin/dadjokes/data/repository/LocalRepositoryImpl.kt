package com.thezayin.dadjokes.data.repository

import com.thezayin.dadjokes.data.local.database.Database
import com.thezayin.dadjokes.domain.local.repository.LocalRepository
import com.thezayin.dadjokes.domain.remote.model.JokesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalRepositoryImpl(
    database: Database
) : LocalRepository {
    private val daoImpl = database.dao()

    override suspend fun getAllJokes(): Flow<List<JokesModel>> = flow {
        try {
            emit(daoImpl.getAllJokes())
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override suspend fun getJokeById(id: String): Flow<JokesModel> = flow {
        try {
            emit(daoImpl.getJokeById(id))
        } catch (e: Exception) {
            emit(JokesModel(id = "", joke = "No joke found", status = 0))
        }
    }

    override suspend fun saveJoke(jokesModel: JokesModel): Flow<Boolean> = flow {
        try {
            daoImpl.saveJoke(
                jokesModel
            )
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun deleteJokeById(id: String): Flow<Boolean> = flow {
        try {
            daoImpl.deleteJokeById(id)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun deleteAllJokes(): Flow<Boolean> = flow {
        try {
            daoImpl.deleteAllJokes()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }
}