package com.example.domain.models.response_models

data class UserLoginInResponse(
    val status: Boolean,
    val message: String,
    val isLoggedIn: Boolean
)
