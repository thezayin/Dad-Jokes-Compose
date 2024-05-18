package com.thezayin.dadjokes.domain.local.usecase

import com.thezayin.dadjokes.domain.local.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

interface DeleteJokeUseCase : suspend (String) -> Flow<Boolean>

class DeleteJokeUseCaseImpl(
    private val repository: LocalRepository
) : DeleteJokeUseCase {
    override suspend fun invoke(id: String): Flow<Boolean> = repository.deleteJokeById(id)
}