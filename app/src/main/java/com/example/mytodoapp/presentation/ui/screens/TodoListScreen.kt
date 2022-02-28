package com.example.mytodoapp.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.TodoItem
import com.example.mytodoapp.presentation.ui.ScreenTransitions
import com.example.mytodoapp.presentation.ui.theme.Shapes
import com.example.mytodoapp.presentation.ui.viewmodels.TodoListScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalAnimationApi::class)
@Destination(style = ScreenTransitions::class)
@Composable
fun TodoListScreen(
    navController: DestinationsNavigator,
    viewModel: TodoListScreenViewModel = hiltViewModel(),
) {
    val values = viewModel.listOfTodos.value
    var subTaskVisibility by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            items(values) {
                TodoScreenItem(todoItem = it, subTaskVisibility = subTaskVisibility) { visibility ->
                    subTaskVisibility = !visibility
                }
            }
        }
    }

}

@Composable
fun TodoScreenItem(
    todoItem: TodoItem,
    subTaskVisibility: Boolean,
    onSubTaskVisible: (Boolean) -> Unit,
) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(12.dp),
        elevation = 5.dp,
        shape = Shapes.large
    ) {
        Column(Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)) {
            Text(text = todoItem.task,
                fontSize = 22.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
            Text(
                text = todoItem.description,
                fontSize = 22.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )
            if (todoItem.subTasks.isNotEmpty()) {
                IconButton(
                    onClick = { onSubTaskVisible(subTaskVisibility) },
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    content = {
                        Image(imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow")
                    }
                )
                AnimatedVisibility(visible = subTaskVisibility,
                    enter = slideInHorizontally(animationSpec = tween(500)),
                    exit = slideOutHorizontally(animationSpec = tween(500))) {
                    LazyRow(Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()) {
                        items(todoItem.subTasks) {
                            Text(text = it.name)
                        }
                    }
                }
            }
        }
    }
}
