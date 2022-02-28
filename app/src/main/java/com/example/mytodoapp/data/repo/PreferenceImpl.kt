package com.example.mytodoapp.data.repo

import com.example.domain.repository.Preference
import com.example.mytodoapp.util.Constants
import com.pixplicity.easyprefs.library.Prefs
import javax.inject.Inject

class PreferenceImpl @Inject constructor(

): Preference{
    override fun saveUserId(userId: String) {
        Prefs.putString(Constants.USER_ID, userId)
    }

    override fun getUserId(): String {
        return Prefs.getString(Constants.USER_ID)
    }

    override fun savePassword(pass: String) {
        Prefs.putString(Constants.PASSWORD, pass)
    }

    override fun getPassword(): String {
        return Prefs.getString(Constants.PASSWORD)
    }
}