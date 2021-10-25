package com.example.service

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.button_start).setOnClickListener {
            startService()
        }

        findViewById<Button>(R.id.button_end).setOnClickListener {
            stopService(OverlayService.newService(this))
        }

    }

    private fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(OverlayService.newService(this))
        } else {
            startService(OverlayService.newService(this))
        }
    }
}
