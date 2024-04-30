package com.thezayin.dadjokes.domain.local.usecase

import com.thezayin.dadjokes.domain.local.repository.LocalRepository
import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.core.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetJokeUseCase : suspend (String) -> Flow<Response<JokesModel>>

class GetJokeUseCaseImpl(
    private val repository: LocalRepository
) : GetJokeUseCase {
    override suspend fun invoke(id: String): Flow<Response<JokesModel>> = repository.getJokeById(id)
}