package com.example.til.clipboard

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class ClipboardActivity : AppCompatActivity() {

    private val clipboard = Clipboard()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clipboard)


        startService(Intent(this, MyAccessibilityService::class.java))
        startService(Intent(this, ClipboardService::class.java))

        findViewById<Button>(R.id.clipboard_btn).setOnClickListener {
            Log.d("결과", clipboard.getClipboardText(this).toString())
        }

        findViewById<Button>(R.id.clipboard_clear_btn).setOnClickListener {

        }
    }

}