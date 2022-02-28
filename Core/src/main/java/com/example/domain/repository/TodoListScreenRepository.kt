package com.example.domain.repository

import com.example.common.Resource
import com.example.domain.models.response_models.TodoListResponse

interface TodoListScreenRepository {
    suspend fun getAllTodos(userId: String): Resource<TodoListResponse>
}