package com.example.common

enum class Action(val value: String?) {
    LOGIN_SUCCESSFUL("LOGIN_SUCCESSFULL"),
    REGISTER("REGISTER"),
    ERROR("ERROR"),
    WARN("WARN"),
    LIST_OF_TODOS("LIST_OF_TODOS"),
    TODO_DETAILS("TODO_DETAILS"),
    LOADING("LOADING"),
}