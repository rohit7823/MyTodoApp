package com.example.domain.repository.remote_service

import com.example.common.Resource
import com.example.domain.models.response_models.TodoDetailsResponse

interface TodoDetailsApi {
    suspend fun getTodoDetails(todoId: String): TodoDetailsResponse
}