package com.example.perference

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
        //읽기.
        val exampleCounterFlow: Flow<Int> = this.dataStore.data.map { preferences ->
            preferences[EXAMPLE_COUNTER] ?: 0
        }
    }

    //쓰기
    private suspend fun incrementCounter(key : Preferences.Key<Int>) {
        dataStore.edit { settings ->
            val currentCounterValue = settings[key] ?: 0
            settings[key] = currentCounterValue + 1
        }
    }
}

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")