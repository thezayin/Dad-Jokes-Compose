package com.thezayin.dadjokes.screens.ai.domain.usecase

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import com.thezayin.dadjokes.screens.ai.domain.repository.AIContentRepository
import kotlinx.coroutines.flow.Flow

interface GetAIContentWithDescriptionUseCae {
    suspend operator fun invoke(description: String): Flow<Response<List<AIContentModel>>>
}

class GetAIContentWithDescriptionUseCaseImpl(
    private val repository: AIContentRepository
) : GetAIContentWithDescriptionUseCae {
    override suspend fun invoke(description: String): Flow<Response<List<AIContentModel>>> =
        repository.getGeneratedAIContentWithDescription(description)
}