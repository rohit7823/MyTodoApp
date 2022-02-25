package com.example.domain.models

data class SubTask(
    val name: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val weightage: Int,
    val reminder: Boolean
)
