package com.example.mytodoapp.util

class Event<out T>(private val content: T) {


    var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (!hasBeenHandled) {
            hasBeenHandled = true
            content

        } else {
            null
        }
    }

    fun peekContent(): T = content
}