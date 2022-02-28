package com.example.mytodoapp.presentation.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytodoapp.presentation.ui.ScreenTransitions
import com.example.mytodoapp.presentation.ui.viewmodels.TodoDetailViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalAnimationApi
@Destination(style = ScreenTransitions::class)
@Composable
fun TodoDetailScreen(
    navController: DestinationsNavigator,
    viewModel: TodoDetailViewModel = hiltViewModel(),
) {


    Column(Modifier.fillMaxSize()) {

    }


}