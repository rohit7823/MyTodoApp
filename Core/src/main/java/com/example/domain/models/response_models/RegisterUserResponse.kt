package com.example.domain.models.response_models

data class RegisterUserResponse(
    val status: Boolean,
    val message: String,
    val isUserRegistered: Boolean,
    val userId: String
)
