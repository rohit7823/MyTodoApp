package com.example.mytodoapp.presentation.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Action
import com.example.common.Screen
import com.example.domain.use_cases.LoginScreenUseCases
import com.example.mytodoapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginScreenUseCases: LoginScreenUseCases
) : ViewModel() {

    val goNextScreen = mutableStateOf<Screen?>(null)
    var userName = mutableStateOf("")
    var password = mutableStateOf("")

    private val _toastNotify = mutableStateOf("")
    val toastNotify: State<String> = _toastNotify

    fun onLoginClicked() {
        loginScreenUseCases.login(userName = userName.value, passWord = password.value).onEach {
            when (it.actionType) {
                Action.LOGIN_SUCCESSFUL -> {
                    it.targetType?.let {
                        val screen = it as? Screen
                        screen?.let { scr->
                            goNextScreen?.value = scr
                        }
                    }
                }
                Action.REGISTER -> {
                    it.targetType?.let {
                        val screen = it as? Screen
                        screen?.let { scr->
                            goNextScreen?.value = scr
                        }
                    }
                }
                Action.ERROR -> {
                    it.targetType?.let {
                        val msg = it as? String
                        msg?.let {
                            _toastNotify.value = it
                        }
                    }
                }
                Action.WARN -> {
                    it.targetType?.let {
                        val msg = it as? String
                        msg?.let {
                            _toastNotify.value = it
                        }
                    }
                }
            }


        }.launchIn(viewModelScope)
    }


}