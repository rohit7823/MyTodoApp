package com.example.mytodoapp.data.remote

import com.example.common.HttpRoutes
import com.example.domain.models.SubTask
import com.example.domain.models.TodoItem
import com.example.domain.models.response_models.TodoListResponse
import com.example.domain.repository.remote_service.TodoListApi
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import javax.inject.Inject

class TodoListApiImpl @Inject constructor(
    private val client: HttpClient
) : TodoListApi {
    override suspend fun getAllTodos(userId: String): TodoListResponse {

        return client.post {
            url { HttpRoutes.TODO_LIST }
            contentType(ContentType.Application.FormUrlEncoded)
            body = userId
        }


    }
}