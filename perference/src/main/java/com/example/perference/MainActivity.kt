package com.example.perference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.preference_module.PreferenceModule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (PreferenceModule.getInstance(this).getStartApp()) {
            Log.d("결과", "true")
        } else {
            Log.d("결과", "false")
            PreferenceModule.getInstance(this).setStartApp(true)
        }
    }
}