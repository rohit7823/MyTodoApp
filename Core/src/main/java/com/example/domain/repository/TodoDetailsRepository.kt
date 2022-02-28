package com.example.domain.repository

import com.example.common.Resource
import com.example.domain.models.response_models.TodoDetailsResponse

interface TodoDetailsRepository {
    suspend fun getTodoDetails(todoId: String): Resource<TodoDetailsResponse>
}