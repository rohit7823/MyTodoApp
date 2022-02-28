package com.example.mytodoapp.data.repo

import com.example.common.Resource
import com.example.domain.models.SubTask
import com.example.domain.models.TodoItem
import com.example.domain.models.response_models.TodoListResponse
import com.example.domain.repository.TodoListScreenRepository
import com.example.domain.repository.remote_service.TodoListApi
import com.example.mytodoapp.util.Weightage
import javax.inject.Inject

class TodoListRepositoryImpl @Inject constructor(
    private val todoListApi: TodoListApi
): TodoListScreenRepository {
    override suspend fun getAllTodos(userId: String): Resource<TodoListResponse> {
        return Resource.Success(
            TodoListResponse(
                status = true,
                message = "Successfull",
                listOfTodos = listOf(
                    TodoItem(
                        "Task 1",
                        subTasks = listOf(
                            SubTask(
                                "Sub task 1",
                                "adfpdnfdpaonpaovmnpas;ldvmaksvnpasohpoahiwgaponasvldknLcmn",
                                System.currentTimeMillis(),
                                System.currentTimeMillis()*1000,
                                Weightage.MEDIUM.ordinal,
                                false
                            )
                        ),
                        description = "adspofjpefoapsncpoascnpaosdpoqdjwpodjpqmnpqwkncq",
                        System.currentTimeMillis(),
                        System.currentTimeMillis()*1000,
                        Weightage.HIGH.ordinal,
                        true
                    ),
                    TodoItem(
                        "Task 2",
                        subTasks = emptyList(),
                        description = "adspofjpefoapsncpoascnpaosdpoqdjwpodjpqmnpqwkncq",
                        System.currentTimeMillis(),
                        System.currentTimeMillis()*1000,
                        Weightage.MEDIUM.ordinal,
                        false
                    )
                )
            )
        )
    }
}