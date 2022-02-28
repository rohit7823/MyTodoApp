package com.example.common

interface TextProvider {
    fun getText(id: TextIdentity): String
}
