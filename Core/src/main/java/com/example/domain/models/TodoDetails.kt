package com.example.domain.models

data class TodoDetails(
    val task: String,
    val subTasks: List<SubTask>,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val weightage: Int,
    val reminder: Boolean
)
