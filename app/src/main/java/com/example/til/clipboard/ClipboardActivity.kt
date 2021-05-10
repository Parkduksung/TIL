package com.example.til.clipboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class ClipboardActivity : AppCompatActivity() {

    private val clipboard = Clipboard()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clipboard)

        startService(Intent(this, ClipboardService::class.java))
    }

    override fun onDestroy() {
        stopService(Intent(this, ClipboardService::class.java))
        super.onDestroy()
    }

    override fun onResume() {
        Log.d("결과", clipboard.getClipboardText(this)?: "Null")
        super.onResume()
    }
}