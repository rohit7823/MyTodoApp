package com.example.domain.use_cases

import com.example.common.*
import com.example.domain.models.response_models.TodoListResponse
import com.example.domain.repository.Preference
import com.example.domain.repository.TodoListScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoListScreenUseCases @Inject constructor(
    private val textProvider: TextProvider,
    private val preference: Preference,
    private val todoListScreenRepository: TodoListScreenRepository
) {

    fun getAllTodos(): Flow<Command> = flow {
        val userId = preference.getUserId()


        when {
            userId.isNotEmpty() -> {
                val response = todoListScreenRepository.getAllTodos(userId)
                if(response.data != null) {
                    if(response.data.status) {
                        val c = Command(Action.LIST_OF_TODOS, response.data.listOfTodos)
                        emit(c)
                    }
                    else {
                        val c = Command(Action.ERROR, response.data.message)
                        emit(c)
                    }
                }
                else {
                    val c = Command(Action.ERROR, textProvider.getText(TextIdentity.SOMETHING_WRONG))
                    emit(c)
                }
            }
        }

    }


}