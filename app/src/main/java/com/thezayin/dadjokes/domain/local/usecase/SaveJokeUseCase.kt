package com.thezayin.dadjokes.domain.local.usecase

import com.thezayin.dadjokes.domain.local.repository.LocalRepository
import com.thezayin.dadjokes.domain.model.JokesModel
import kotlinx.coroutines.flow.Flow

interface SaveJokeUseCase : suspend (JokesModel) -> Flow<Boolean>
class SaveJokeUseCaseImpl(
    private val repository: LocalRepository
) : SaveJokeUseCase {
    override suspend fun invoke(jokesModel: JokesModel): Flow<Boolean> =
        repository.saveJoke(jokesModel)
}