package com.example.mytodoapp.util

import androidx.annotation.StringRes
import com.example.mytodoapp.application.TodoApp


fun Int.string(): String {
    return TodoApp.instance?.stringResource(this) ?: ""
}

fun convertToString(@StringRes id: Int): String {
    return TodoApp.instance?.stringResource(id) ?: ""
}