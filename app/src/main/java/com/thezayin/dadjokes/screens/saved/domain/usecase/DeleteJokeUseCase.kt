package com.thezayin.dadjokes.screens.saved.domain.usecase

import com.thezayin.dadjokes.screens.saved.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

interface DeleteJokeUseCase : suspend (String) -> Flow<Boolean>

class DeleteJokeUseCaseImpl(
    private val repository: LocalRepository
) : DeleteJokeUseCase {
    override suspend fun invoke(id: String): Flow<Boolean> = repository.deleteJokeById(id)
}