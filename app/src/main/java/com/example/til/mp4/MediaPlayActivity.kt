package com.example.til.mp4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class MediaPlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        findViewById<Button>(R.id.start_video_btn).setOnClickListener {
            startActivity(Intent(this, MP4MainActivity::class.java))
        }
    }
}