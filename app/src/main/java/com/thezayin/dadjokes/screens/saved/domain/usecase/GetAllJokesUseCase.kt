package com.thezayin.dadjokes.screens.saved.domain.usecase

import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import com.thezayin.dadjokes.screens.saved.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

interface GetAllJokesUseCase : suspend () -> Flow<List<JokesModel>>

class GetAllJokesUseCaseImpl(
    private val repository: LocalRepository
) : GetAllJokesUseCase {
    override suspend fun invoke(): Flow<List<JokesModel>> = repository.getAllJokes()
}