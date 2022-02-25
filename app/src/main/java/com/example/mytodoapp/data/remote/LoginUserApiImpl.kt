package com.example.mytodoapp.data.remote

import com.example.common.HttpRoutes

import com.example.domain.models.response_models.UserLoginInResponse
import com.example.domain.repository.remote_service.LoginUserApi
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class LoginUserApiImpl @Inject constructor(
    private val client: HttpClient
) : LoginUserApi {
    override suspend fun loginUser(userId: String): UserLoginInResponse {
        return client.post {
            url { HttpRoutes.LOGIN }
            contentType(ContentType.Application.FormUrlEncoded)
            body = userId
        }
    }
}