package com.thezayin.dadjokes.screens.ai.data.repository

import com.thezayin.dadjokes.core.framework.utils.Response
import com.thezayin.dadjokes.screens.ai.data.remote.AIService
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import com.thezayin.dadjokes.screens.ai.domain.model.AIRequestWithDescription
import com.thezayin.dadjokes.screens.ai.domain.model.AIRequestWithDetails
import com.thezayin.dadjokes.screens.ai.domain.repository.AIContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class AIContentRepositoryImpl(
    private val aiService: AIService
) : AIContentRepository {

    override suspend fun getGeneratedAIContentWithDetails(
        type: String, format: String, description: String?
    ): Flow<Response<List<AIContentModel>>> = flow {
        emit(Response.Loading)
        try {
            val request = AIRequestWithDetails(type, format, description)
            val response = aiService.getAIContentWithDetails(request)
            val responseText =
                response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: ""
            if (responseText.isNotEmpty()) {
                emit(Response.Success(listOf(AIContentModel(content = responseText))))
            } else {
                emit(Response.Error("No content found."))
            }
        } catch (e: Exception) {
            Timber.e("Error: $e")
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override suspend fun getGeneratedAIContentWithDescription(
        description: String
    ): Flow<Response<List<AIContentModel>>> = flow {
        emit(Response.Loading)
        try {
            val prompt = "Generate a funny joke based on the following description: $description"
            val request = AIRequestWithDescription(prompt)
            val response = aiService.getAIContentWithDescription(request)
            val responseText =
                response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: ""
            if (responseText.isNotEmpty()) {
                val splitText = responseText.split(
                    "\n",
                    limit = 2
                )
                val title = splitText.getOrElse(0) { "" }
                val description = splitText.getOrElse(1) { "" }

                val contentModel = AIContentModel(
                    content = responseText,
                    title = title.trim(),
                    description = description.trim()
                )
                emit(Response.Success(listOf(contentModel)))
            } else {
                emit(Response.Error("No valid content found in the AI response."))
            }
        } catch (e: Exception) {
            Timber.e("Error: $e")
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}
