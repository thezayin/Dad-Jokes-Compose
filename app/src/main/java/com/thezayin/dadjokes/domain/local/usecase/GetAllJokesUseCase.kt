package com.thezayin.dadjokes.domain.local.usecase

import com.thezayin.dadjokes.domain.local.repository.LocalRepository
import com.thezayin.dadjokes.domain.remote.model.JokesModel
import kotlinx.coroutines.flow.Flow

interface GetAllJokesUseCase : suspend () -> Flow<List<JokesModel>>

class GetAllJokesUseCaseImpl(
    private val repository: LocalRepository
) : GetAllJokesUseCase {
    override suspend fun invoke(): Flow<List<JokesModel>> = repository.getAllJokes()
}