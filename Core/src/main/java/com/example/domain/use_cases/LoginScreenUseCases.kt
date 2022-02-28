package com.example.domain.use_cases

import com.example.common.*
import com.example.domain.repository.LoginScreenRepository
import com.example.domain.repository.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginScreenUseCases @Inject constructor(
    private val preference: Preference,
    private val loginScreenRepository: LoginScreenRepository,
    private val textProvider: TextProvider
) {

    fun login(userName: String, passWord: String): Flow<Command> = flow {
        val uN = preference.getUserId()
        val pass = preference.getPassword()

        if(userName.isNotEmpty() && passWord.isNotEmpty()) {

            preference.saveUserId(userName)
            preference.savePassword(passWord)

            val c = Command(Action.LOGIN_SUCCESSFUL, Screen.TODO_LIST)
            emit(c)

            /*if (uN == userName && pass == passWord) {
                val c = Command(Action.LOGIN_SUCCESSFUL, Screen.TODO_LIST)
                emit(c)
            }
            else if(uN.isEmpty() && pass.isEmpty()) {
                val c = Command(Action.REGISTER, Screen.REGISTER)
                emit(c)
            }
            else {
                val c = Command(Action.ERROR, textProvider.getText(TextIdentity.SOMETHING_WRONG))
                emit(c)
            }*/
        }
        else {
            val c = Command(Action.WARN, textProvider.getText(TextIdentity.PLEASE_FILL_ALL_FIELDS))
            emit(c)
        }

    }



}