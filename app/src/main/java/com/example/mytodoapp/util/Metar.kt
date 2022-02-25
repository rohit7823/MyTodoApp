package com.example.mytodoapp.util

import android.content.Context
import android.content.pm.PackageManager
import com.example.mytodoapp.application.TodoApp

object Metar {
    private var todoApp: TodoApp? = null
    operator fun get(key: String): String {
        return try {
            val ai = (todoApp as Context)
                .packageManager
                .getApplicationInfo(
                    (todoApp as Context)
                        .packageName,
                    PackageManager.GET_META_DATA
                )
            val bundle = ai.metaData
            bundle.getString(key)?:""
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }
    }
    fun initialize(app: TodoApp) {
        Metar.todoApp = app
    }
}