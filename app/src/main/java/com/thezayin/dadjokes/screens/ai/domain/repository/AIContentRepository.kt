package com.thezayin.dadjokes.screens.ai.domain.repository

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import kotlinx.coroutines.flow.Flow

interface AIContentRepository {
    suspend fun getGeneratedAIContentWithDetails(
        type: String, format: String, description: String?
    ): Flow<Response<List<AIContentModel>>>

    suspend fun getGeneratedAIContentWithDescription(
        description: String
    ): Flow<Response<List<AIContentModel>>>
}
