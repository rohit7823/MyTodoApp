package com.example.domain.repository.remote_service

import com.example.common.Resource
import com.example.domain.models.User
import com.example.domain.models.response_models.RegisterUserResponse

interface RegisterUserApi {
    suspend fun registerUser(user: User): RegisterUserResponse
}