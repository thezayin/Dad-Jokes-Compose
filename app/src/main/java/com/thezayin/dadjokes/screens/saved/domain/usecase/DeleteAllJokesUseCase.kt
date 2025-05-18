package com.thezayin.dadjokes.screens.saved.domain.usecase

import com.thezayin.dadjokes.screens.saved.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

interface DeleteAllJokesUseCase : suspend () -> Flow<Boolean>
class DeleteAllJokesUseCaseImpl(
    private val repository: LocalRepository
) : DeleteAllJokesUseCase {
    override suspend fun invoke(): Flow<Boolean> = repository.deleteAllJokes()
}