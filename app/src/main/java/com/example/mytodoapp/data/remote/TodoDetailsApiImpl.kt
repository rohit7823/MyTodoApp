package com.example.mytodoapp.data.remote

import com.example.common.HttpRoutes
import com.example.domain.models.response_models.TodoDetailsResponse
import com.example.domain.repository.remote_service.TodoDetailsApi
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class TodoDetailsApiImpl @Inject constructor(
    private val client: HttpClient
) : TodoDetailsApi {
    override suspend fun getTodoDetails(todoId: String): TodoDetailsResponse {
        return client.post {
            url { HttpRoutes.TODO_DETAILS }
            contentType(ContentType.Application.FormUrlEncoded)
            body = todoId
        }
    }
}