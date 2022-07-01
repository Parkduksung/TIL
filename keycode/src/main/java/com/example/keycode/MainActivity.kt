package com.example.keycode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        Log.d("결과", "${event?.keyCode}  ${event?.action}")
        return super.dispatchKeyEvent(event)
    }
}