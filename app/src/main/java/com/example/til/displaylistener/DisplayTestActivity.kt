package com.example.til.displaylistener

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class DisplayTestActivity : AppCompatActivity() {


    private lateinit var displayManager: DisplayManager


    private val displayListener: DisplayManager.DisplayListener by lazy {
        object : DisplayManager.DisplayListener {
            override fun onDisplayAdded(displayId: Int) {

            }

            override fun onDisplayRemoved(displayId: Int) {
            }

            @SuppressLint("NewApi")
            override fun onDisplayChanged(displayId: Int) {
                Log.d("결과", display?.rotation.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_test)

        displayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        displayManager.registerDisplayListener(displayListener, Handler(handlerCallback))

    }

    private val handlerCallback = Handler.Callback {
        Log.d("결과", "handlerCallback")
        true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}