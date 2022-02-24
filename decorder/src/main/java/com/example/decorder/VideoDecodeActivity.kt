package com.example.decorder

import android.content.Context
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.os.Build
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi
import com.example.decorder.base.BaseActivity
import com.example.decorder.databinding.ActivityDecodeBinding

class VideoDecodeActivity : BaseActivity<ActivityDecodeBinding>(R.layout.activity_decode), SurfaceHolder.Callback {

    companion object {

        private lateinit var videoPath: AssetFileDescriptor

        fun newIntent(context: Context, type: VideoType): Intent {
            videoPath = when (type) {
                is VideoType.TYPE_1920X1080 -> {
                    context.resources.openRawResourceFd(R.raw.test_1920x1080)
                }
                is VideoType.TYPE_2560X1440 -> {
                    context.resources.openRawResourceFd(R.raw.test_2560x1440)
                }
            }
            return Intent(context, VideoDecodeActivity::class.java)
        }

    }


    private var videoDecode: VideoDecodeThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.surface.holder.addCallback(this@VideoDecodeActivity)
        videoDecode = VideoDecodeThread()
    }

    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, p2: Int, p3: Int) {
        if (videoDecode?.init(holder.surface, videoPath) == true) {
            videoDecode?.start()
        } else {
            videoDecode = null
        }
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        videoDecode?.close()
    }

    override fun onPause() {
        super.onPause()
        videoDecode?.close()
    }
}

sealed class VideoType {
    object TYPE_1920X1080 : VideoType()
    object TYPE_2560X1440 : VideoType()
}