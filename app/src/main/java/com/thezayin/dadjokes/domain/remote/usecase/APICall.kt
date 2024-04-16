package com.thezayin.dadjokes.domain.remote.usecase

import com.thezayin.dadjokes.domain.remote.model.JokesModel
import com.thezayin.dadjokes.domain.remote.repository.RemoteRepository
import com.thezayin.dadjokes.presentation.utils.Response
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase : suspend () -> Flow<Response<JokesModel>>

class RemoteUseCaseImpl(
    private val repository: RemoteRepository
) : RemoteUseCase {
    override suspend fun invoke() =  repository.getRandomJoke()
}