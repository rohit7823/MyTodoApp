package com.example.domain.repository.remote_service

import com.example.domain.models.response_models.TodoListResponse

interface TodoListApi {
    suspend fun getAllTodos(userId: String): TodoListResponse
}