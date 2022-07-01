package com.example.decorder

import android.media.MediaCodec
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.WindowMetrics
import com.example.decorder.base.BaseActivity
import com.example.decorder.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            btnTest1920x1080.setOnClickListener {
                startActivity(
                    VideoDecodeActivity.newIntent(
                        this@MainActivity,
                        VideoType.TYPE_1920X1080
                    )
                )
            }

            btnTest2560x1440.setOnClickListener {
                startActivity(
                    VideoDecodeActivity.newIntent(
                        this@MainActivity,
                        VideoType.TYPE_2560X1440
                    )
                )
            }
        }


        val supportedCodecSize = SupportedCodecSize(
            MediaCodec.createDecoderByType(MIME_AVC),
            MIME_AVC,
            Size(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
        )

        Log.d("결과 width1", supportedCodecSize.width.toString())
        Log.d("결과 height1", supportedCodecSize.height.toString())


    }

    companion object {
        private const val MIME_AVC = "video/avc"
    }
}