package com.example.mytodoapp.presentation.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Action
import com.example.domain.models.TodoItem
import com.example.domain.use_cases.TodoListScreenUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TodoListScreenViewModel @Inject constructor(
    private val todoListScreenUseCases: TodoListScreenUseCases
): ViewModel() {

    private val _listOfTodos = mutableStateOf<List<TodoItem>>(emptyList())
    val listOfTodos: State<List<TodoItem>> = _listOfTodos

    val toastNotify = mutableStateOf("")

    init {
        getAllTodos()
    }

    fun getAllTodos() {
        todoListScreenUseCases.getAllTodos().onEach {
            when(it.actionType) {
                Action.LIST_OF_TODOS -> {
                    it.targetType?.let {
                        val list = it as? List<TodoItem>
                        list?.let{ tI->
                            _listOfTodos.value = tI
                        }
                    }
                }
                Action.ERROR -> {
                    it.targetType?.let {
                        val msg = it as? String
                        msg?.let {
                            toastNotify.value = it
                        }
                    }
                }

            }
        }.launchIn(viewModelScope)
    }




}