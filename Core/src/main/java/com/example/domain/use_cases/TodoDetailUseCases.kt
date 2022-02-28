package com.example.domain.use_cases

import com.example.common.*
import com.example.common.HttpRoutes.TODO_DETAILS
import com.example.domain.models.response_models.TodoDetailsResponse
import com.example.domain.repository.Preference
import com.example.domain.repository.TodoDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoDetailUseCases @Inject constructor(
    private val todoDetailsRepository: TodoDetailsRepository,
    private val preference: Preference,
    private val textProvider: TextProvider
) {

    fun getTodoDetails(todoID: String): Flow<Command> = flow {
        val userId = preference.getUserId()

        val response = todoDetailsRepository.getTodoDetails(todoID)

        when(response){
            is Resource.Success<TodoDetailsResponse> -> {
                if (response.data?.status == true) {
                    val c = Command(Action.TODO_DETAILS, response.data.todoDetails)
                    emit(c)
                }
            }
            is Resource.Error<TodoDetailsResponse> -> {
                val c = Command(Action.ERROR, textProvider.getText(TextIdentity.SOMETHING_WRONG))
                emit(c)
            }
            else -> {
                val c = Command(Action.LOADING, textProvider.getText(TextIdentity.CURRENTLY_LOADING))
                emit(c)
            }
        }

    }

}