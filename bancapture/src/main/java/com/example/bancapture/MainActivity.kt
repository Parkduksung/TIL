package com.example.bancapture

import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun captureOk(view: View) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    fun captureNo(view: View) {
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }
}