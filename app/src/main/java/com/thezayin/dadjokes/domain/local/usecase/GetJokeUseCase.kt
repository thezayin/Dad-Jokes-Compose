package com.thezayin.dadjokes.domain.local.usecase

import com.thezayin.dadjokes.domain.local.repository.LocalRepository
import com.thezayin.dadjokes.domain.remote.model.JokesModel
import kotlinx.coroutines.flow.Flow

interface GetJokeUseCase : suspend (String) -> Flow<JokesModel>

class GetJokeUseCaseImpl(
    private val repository: LocalRepository
) : GetJokeUseCase {
    override suspend fun invoke(id: String): Flow<JokesModel> = repository.getJokeById(id)
}