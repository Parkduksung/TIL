package com.example.perference

import android.app.Activity
import android.content.Context
import androidx.core.content.edit

class ConfigRepository(private val context: Context) {

    companion object {

        private const val PREF_START = "rsflag.isstartapp"
        private const val START_KEY_IS_STARTED = "isstart"
    }

    private fun getStartAppPref() = context.getSharedPreferences(PREF_START, Activity.MODE_PRIVATE)

    
    fun setStartApp(started: Boolean) {
        getStartAppPref().edit {
            putBoolean(START_KEY_IS_STARTED, started)
        }
    }

    fun getStartApp(): Boolean {
        return getStartAppPref().getBoolean(START_KEY_IS_STARTED, false)
    }

}