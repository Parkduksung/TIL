package com.example.til.clipboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class ClipboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clipboard)

        startService(Intent(this, ClipboardService::class.java))

    }

    override fun onDestroy() {
        stopService(Intent(this, ClipboardService::class.java))
        super.onDestroy()
    }

}