package com.example.til.clipboard

import android.content.ClipboardManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class ClipboardActivity : AppCompatActivity() {

    private val clipboard by lazy { Clipboard() }

    private val clipChangedListener = ClipboardManager.OnPrimaryClipChangedListener { Log.d("결과", "복사됨...") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clipboard)

        clipboard.setClipboardListener(this, clipChangedListener)

        findViewById<Button>(R.id.clipboard_btn).setOnClickListener {
            Toast.makeText(this, clipboard.getClipboardText(this) ?: "널임", Toast.LENGTH_LONG).show()
        }

    }

}