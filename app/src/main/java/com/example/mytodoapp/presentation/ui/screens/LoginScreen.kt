package com.example.mytodoapp.presentation.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.Screen
import com.example.mytodoapp.R
import com.example.mytodoapp.presentation.ui.ScreenTransitions
import com.example.mytodoapp.presentation.ui.screens.destinations.TodoListScreenDestination
import com.example.mytodoapp.presentation.ui.theme.MyTodoAppTheme
import com.example.mytodoapp.presentation.ui.viewmodels.LoginScreenViewModel
import com.example.mytodoapp.util.convertToString
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Destination(start = true, style = ScreenTransitions::class)
@Composable
fun LoginScreen(
    navController: DestinationsNavigator?,
    loginViewModel: LoginScreenViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = convertToString(R.string.sign_in), fontSize = 30.sp)
                TextField(
                    value = loginViewModel.userName.value,
                    onValueChange = {
                        loginViewModel.userName.value = it
                    },
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(10.dp),
                    placeholder = { Text(text = convertToString(R.string.enter_user_id)) },
                )
                TextField(
                    value = loginViewModel.password.value,
                    onValueChange = {
                        loginViewModel.password.value = it
                    },
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(10.dp),
                    placeholder = { Text(text = convertToString(R.string.enter_password)) }
                )
                Button(
                    onClick = {
                        loginViewModel.onLoginClicked()

                        loginViewModel.toastNotify.value.let { msg->
                            if(msg.isNotEmpty()) {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(msg)
                                }
                            }
                        }
                        loginViewModel.goNextScreen.value?.let {
                            when(it){
                                Screen.TODO_LIST -> {
                                    navController?.popBackStack()
                                    navController?.navigate(TodoListScreenDestination)
                                }
                            }
                        }
                    },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(text = convertToString(R.string.login))
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSection() {
    MyTodoAppTheme {
        LoginScreen(navController = null)
    }
}