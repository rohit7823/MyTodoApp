package com.example.mytodoapp.presentation.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Action
import com.example.domain.models.TodoDetails
import com.example.domain.use_cases.TodoDetailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TodoDetailViewModel @Inject constructor(
    private val todoDetailUseCases: TodoDetailUseCases
): ViewModel() {

    private val _todoDetails: MutableState<TodoDetails?> = mutableStateOf(null)
    val todoDetails: State<TodoDetails?> = _todoDetails

    private val _toastNotify: MutableState<String?> = mutableStateOf(null)
    val toastNotify: State<String?> = _toastNotify

    private fun getTodoDetails(todoID: String) {
        todoDetailUseCases.getTodoDetails(todoID = todoID).onEach {
            when(it.actionType) {
                Action.TODO_DETAILS -> {
                    it.targetType?.let {
                        val details = it as? TodoDetails
                        details?.let { td->
                            _todoDetails.value = td
                        }
                    }
                }
                Action.ERROR -> {
                    it.targetType?.let {
                        val msg = it as? String
                        msg?.let { m->
                            _toastNotify.value = m
                        }
                    }
                }
                Action.LOADING -> {
                    it.targetType?.let {
                        val msg = it as? String
                        msg?.let { m ->
                            _toastNotify.value = m
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


}