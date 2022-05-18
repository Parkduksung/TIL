package com.example.preference_module

import android.app.Activity
import android.content.Context
import androidx.core.content.edit
import com.example.preference_module.Constant.PREF_START
import com.example.preference_module.Constant.START_KEY_IS_STARTED

class PreferenceModule(private val context: Context) {

    companion object {

        @Volatile
        private var INSTANCE: PreferenceModule? = null

        fun getInstance(context: Context): PreferenceModule =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PreferenceModule(context).also { INSTANCE = it }
            }
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