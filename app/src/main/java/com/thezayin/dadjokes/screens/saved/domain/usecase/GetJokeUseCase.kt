package com.thezayin.dadjokes.screens.saved.domain.usecase

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import com.thezayin.dadjokes.screens.saved.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

interface GetJokeUseCase : suspend (String) -> Flow<Response<JokesModel>>

class GetJokeUseCaseImpl(
    private val repository: LocalRepository
) : GetJokeUseCase {
    override suspend fun invoke(id: String): Flow<Response<JokesModel>> = repository.getJokeById(id)
}