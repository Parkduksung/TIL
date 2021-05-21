package com.example.til.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.example.til.R

class ClipboardActivity : AppCompatActivity() {

    private val clipboard = Clipboard()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clipboard)

        findViewById<Button>(R.id.clipboard_clear_btn).setOnClickListener {
        }
    }

}