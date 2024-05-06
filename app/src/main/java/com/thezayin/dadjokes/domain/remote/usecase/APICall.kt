package com.thezayin.dadjokes.domain.remote.usecase

import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.dadjokes.domain.remote.repository.RemoteRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase : suspend () -> Flow<Response<JokesModel>>

class RemoteUseCaseImpl(
    private val repository: RemoteRepository
) : RemoteUseCase {
    override suspend fun invoke() = repository.getRandomJoke()
}