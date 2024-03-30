package com.thezayin.dadjokes.domain.repository

import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.dadjokes.presentation.utils.Response
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    suspend fun getRandomJoke(): Flow<Response<JokesModel>>
}