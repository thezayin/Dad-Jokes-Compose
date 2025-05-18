package com.thezayin.dadjokes.screens.ai.domain.usecase

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import com.thezayin.dadjokes.screens.ai.domain.repository.AIContentRepository
import kotlinx.coroutines.flow.Flow

interface GetGeneratedAIWithDetailsUseCase {
    suspend operator fun invoke(
        type: String, format: String, description: String?
    ): Flow<Response<List<AIContentModel>>>

}

class GetGeneratedAIWithDetailsUseCaseImpl(
    private val repository: AIContentRepository
) : GetGeneratedAIWithDetailsUseCase {
    override suspend fun invoke(
        type: String, format: String, description: String?
    ): Flow<Response<List<AIContentModel>>> =
        repository.getGeneratedAIContentWithDetails(type, format, description)
}
