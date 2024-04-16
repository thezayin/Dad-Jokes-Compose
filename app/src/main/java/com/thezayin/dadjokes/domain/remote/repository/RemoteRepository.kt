package com.thezayin.dadjokes.domain.remote.repository

import com.thezayin.dadjokes.domain.remote.model.JokesModel
import com.thezayin.dadjokes.presentation.utils.Response
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getRandomJoke(): Flow<Response<JokesModel>>
}