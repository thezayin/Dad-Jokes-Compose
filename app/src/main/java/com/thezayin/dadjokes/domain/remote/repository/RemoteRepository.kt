package com.thezayin.dadjokes.domain.remote.repository

import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.core.utils.Response
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getRandomJoke(): Flow<Response<JokesModel>>
}