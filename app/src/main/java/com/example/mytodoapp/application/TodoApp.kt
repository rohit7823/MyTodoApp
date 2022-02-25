package com.example.mytodoapp.application

import android.app.Application
import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytodoapp.R
import com.example.mytodoapp.util.ConnectivityListener
import com.example.mytodoapp.util.Metar
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        ConnectivityListener(this).net.observeForever {
            _connectivity.postValue(it)
        }

        Metar.initialize(this)
    }

    companion object {
        var instance: TodoApp? = null
        private val _connectivity = MutableLiveData<ConnectivityListener.Net>()
        val connectivity: LiveData<ConnectivityListener.Net> = _connectivity
    }

    fun stringResource(@StringRes stringId: Int):String
    {
        return try {
            resources.getString(stringId)
        } catch (e: Exception) {
            resources.getString(R.string.error_string)
        }
    }

    fun drawableResource(@DrawableRes drawableId: Int): Drawable?
    {
        return try {
            ContextCompat.getDrawable(this,drawableId)
        } catch (e: Exception) {
            null
        }
    }

    fun dimensionResource(@DimenRes dimenRes: Int):Float?
    {
        return try {
            resources.getDimension(dimenRes)
        } catch (e: Exception) {
            0f
        }
    }

    fun colorResource(@ColorRes colorId: Int):Int
    {
        return try {
            ContextCompat.getColor(this,colorId)
        } catch (e: Exception) {
            Color.TRANSPARENT
        }
    }

}