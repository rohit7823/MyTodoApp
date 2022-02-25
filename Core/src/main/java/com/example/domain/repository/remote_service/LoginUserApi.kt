package com.example.domain.repository.remote_service

import com.example.common.Resource
import com.example.domain.models.response_models.UserLoginInResponse

interface LoginUserApi {
    suspend fun loginUser(userId: String): UserLoginInResponse
}