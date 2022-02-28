package com.example.mytodoapp.data.repo

import com.example.common.TextIdentity
import com.example.common.TextProvider
import com.example.mytodoapp.R
import com.example.mytodoapp.util.string

class TextProviderImpl : TextProvider {
    override fun getText(id: TextIdentity): String {
        return when(id) {
            TextIdentity.SOMETHING_WRONG -> R.string.something_went_wrong.string()
            TextIdentity.LOGIN_SUCCESSFUL -> R.string.login_successfull.string()
            TextIdentity.PLEASE_FILL_ALL_FIELDS ->  R.string.please_fill_all_fields.string()
            else -> ""
        }
    }
}