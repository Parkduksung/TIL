package com.example.storage

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "RemoteView"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RLog.d("안녕하세요1")

        findViewById<Button>(R.id.button_search).setOnClickListener {
            RLog.saveLog {

            }
        }

    }
}
