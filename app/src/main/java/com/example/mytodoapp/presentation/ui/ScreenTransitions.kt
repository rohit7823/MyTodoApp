package com.example.mytodoapp.presentation.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.example.mytodoapp.presentation.ui.screens.destinations.LoginScreenDestination
import com.example.mytodoapp.presentation.ui.screens.destinations.TodoListScreenDestination
import com.example.mytodoapp.presentation.ui.screens.navDestination
import com.ramcosta.composedestinations.spec.DestinationStyle


@ExperimentalAnimationApi
object ScreenTransitions: DestinationStyle.Animated  {

    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        return when(initialState.navDestination) {
            LoginScreenDestination -> {
                slideInHorizontally(
                    initialOffsetX = {1000},
                    animationSpec = tween(700)
                )
            }
            TodoListScreenDestination -> {
                slideInHorizontally(
                    initialOffsetX = {1000},
                    animationSpec = tween(700)
                )
            }
            else -> null
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        return when(initialState.navDestination) {
            LoginScreenDestination -> {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(700)
                )
            }
            TodoListScreenDestination -> {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(700)
                )
            }
            else -> null
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition? {
        return when(initialState.navDestination) {
            LoginScreenDestination -> {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(700)
                )
            }
            TodoListScreenDestination -> {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(700)
                )
            }
            else -> null
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition? {
        return when(initialState.navDestination) {
            LoginScreenDestination -> {
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = tween(700)
                )
            }
            TodoListScreenDestination -> {
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = tween(700)
                )
            }
            else -> null
        }
    }


}