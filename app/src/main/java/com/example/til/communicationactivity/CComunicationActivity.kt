package com.example.til.communicationactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class CCommunicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicate_c)
        findViewById<Button>(R.id.start_btn).setOnClickListener {
            startACommunicationActivity()
        }
    }

    private fun startACommunicationActivity() {
        val intent = Intent(this, ACommunicationActivity::class.java)
        setResult(RESULT_OK, intent)
        finish()
    }
}