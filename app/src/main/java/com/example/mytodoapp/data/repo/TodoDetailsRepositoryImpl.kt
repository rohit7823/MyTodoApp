package com.example.mytodoapp.data.repo

import com.example.common.Resource
import com.example.domain.models.SubTask
import com.example.domain.models.TodoDetails
import com.example.domain.models.response_models.TodoDetailsResponse
import com.example.domain.repository.TodoDetailsRepository
import com.example.domain.repository.remote_service.TodoDetailsApi
import com.example.mytodoapp.util.Weightage
import kotlinx.coroutines.delay
import javax.inject.Inject

class TodoDetailsRepositoryImpl @Inject constructor(
    private val todoDetailsApi: TodoDetailsApi
): TodoDetailsRepository {
    override suspend fun getTodoDetails(todoId: String): Resource<TodoDetailsResponse> {
        delay(1000L)
        return Resource.Success(
            TodoDetailsResponse(
                status = true,
                message = "Successful",
                todoDetails = TodoDetails(
                    task = "Task $todoId",
                    subTasks = listOf(
                        SubTask(
                            name = "Sub-Task 1",
                            description = "pqowjpqwonpowqkdowkqnoksnadskamnksjadnfoqifhpqfq",
                            startTime = System.currentTimeMillis(),
                            endTime = System.currentTimeMillis()*1000,
                            weightage = Weightage.LOW.ordinal,
                            reminder = false
                        )
                    ),
                    description = "adspasodq[poflpmewkcpwaopawofjupamnlsdnkld,mv naldswkioaiwpowejfpohfpjwa",
                    startTime = System.currentTimeMillis(),
                    endTime = System.currentTimeMillis()*1000,
                    weightage = Weightage.HIGH.ordinal,
                    reminder = true
                )
            )
        )
    }
}