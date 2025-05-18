package com.thezayin.dadjokes.screens.home.domain.repository

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    suspend fun fetchRandomJoke(): Flow<Response<JokesModel>>
}