package com.thezayin.dadjokes.screens.saved.domain.repository

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getAllJokes(): Flow<List<JokesModel>>

    suspend fun getJokeById(id: String): Flow<Response<JokesModel>>

    suspend fun saveJoke(jokesModel: JokesModel): Flow<Boolean>

    suspend fun deleteJokeById(id: String): Flow<Boolean>

    suspend fun deleteAllJokes(): Flow<Boolean>
}