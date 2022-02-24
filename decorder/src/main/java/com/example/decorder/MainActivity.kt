package com.example.decorder

import android.os.Bundle
import com.example.decorder.base.BaseActivity
import com.example.decorder.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            btnTest1920x1080.setOnClickListener {
                startActivity(VideoDecodeActivity.newIntent(this@MainActivity, VideoType.TYPE_1920X1080))
            }

            btnTest2560x1440.setOnClickListener {
                startActivity(VideoDecodeActivity.newIntent(this@MainActivity, VideoType.TYPE_2560X1440))
            }
        }
    }
}