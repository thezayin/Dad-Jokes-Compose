package com.thezayin.dadjokes.domain.usecase

import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.dadjokes.domain.repository.JokesRepository
import com.thezayin.dadjokes.presentation.utils.Response
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase : suspend () -> Flow<Response<JokesModel>>

class RemoteUseCaseImpl(
    private val repository: JokesRepository
) : RemoteUseCase {
    override suspend fun invoke() =  repository.getRandomJoke()
}