package com.example.domain.models.response_models

import com.example.domain.models.TodoItem

data class TodoListResponse(
    val status: Boolean,
    val message: String,
    val listOfTodos: List<TodoItem>
)
