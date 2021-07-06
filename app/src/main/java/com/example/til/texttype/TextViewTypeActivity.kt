package com.example.til.texttype

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class TextViewTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_textview_type)
        findViewById<TextView>(R.id.text_marquee).isSelected = true
    }
}