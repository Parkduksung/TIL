package com.example.til.mp4

import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class MediaPlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mp4)


        findViewById<VideoView>(R.id.test_video).apply {
            setVideoPath(MEDIA_URL)
            start()
        }

    }

    companion object{

        private const val MEDIA_URL = "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"

    }
}