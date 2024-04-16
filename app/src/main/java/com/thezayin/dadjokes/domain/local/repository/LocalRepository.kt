package com.thezayin.dadjokes.domain.local.repository

import com.thezayin.dadjokes.domain.remote.model.JokesModel
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getAllJokes(): Flow<List<JokesModel>>

    suspend fun getJokeById(id: String): Flow<JokesModel>

    suspend fun saveJoke(jokesModel: JokesModel): Flow<Boolean>

    suspend fun deleteJokeById(id: String): Flow<Boolean>

    suspend fun deleteAllJokes(): Flow<Boolean>
}