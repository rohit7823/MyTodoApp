package com.example.domain.models.response_models

import com.example.domain.models.TodoDetails

data class TodoDetailsResponse(
    val status : Boolean,
    val message: String,
    val todoDetails: TodoDetails
)
