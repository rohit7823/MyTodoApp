package com.example.mytodoapp.data.remote

import com.example.common.HttpRoutes
import com.example.common.Resource
import com.example.domain.models.User
import com.example.domain.models.response_models.RegisterUserResponse
import com.example.domain.repository.remote_service.RegisterUserApi
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject
import kotlin.text.get

class RegisterUserApiImpl @Inject constructor(
    private val client: HttpClient
): RegisterUserApi {
    override suspend fun registerUser(user: User): RegisterUserResponse {
        return client.post {
            url { path(HttpRoutes.REGISTER) }
            contentType(ContentType.Application.FormUrlEncoded)
            contentType(ContentType.Application.Json)
            body = user
        }
    }
}