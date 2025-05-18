package com.thezayin.dadjokes.screens.saved.domain.usecase

import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import com.thezayin.dadjokes.screens.saved.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

interface SaveJokeUseCase : suspend (JokesModel) -> Flow<Boolean>
class SaveJokeUseCaseImpl(
    private val repository: LocalRepository
) : SaveJokeUseCase {
    override suspend fun invoke(jokesModel: JokesModel): Flow<Boolean> =
        repository.saveJoke(jokesModel)
}