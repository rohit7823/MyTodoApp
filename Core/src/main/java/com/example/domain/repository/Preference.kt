package com.example.domain.repository

interface Preference {
    fun saveUserId(userId: String)
    fun getUserId(): String

    fun savePassword(pass: String)
    fun getPassword(): String
}