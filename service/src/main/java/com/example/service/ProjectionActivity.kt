package com.example.service

import android.annotation.TargetApi
import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast

@TargetApi(21)
class ProjectionActivity : Activity() {

    private val PROJECTION_CODE = 1000
    private var projectionManager: MediaProjectionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.addFlags(
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
//            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        }

        Handler(Looper.getMainLooper()).postDelayed(
            {
                projectionManager =
                    getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
                val intent = projectionManager!!.createScreenCaptureIntent()
                startActivityForResult(intent, PROJECTION_CODE)
            }, 2000L
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode != PROJECTION_CODE) {
            Log.d("결과", "권한 x")
            finish()
            return
        }
        if (resultCode != RESULT_OK) {
            Log.d("결과", "result x")
            finish()
            return
        }
        Log.d("결과", "권한 o")
        finish()
    }
}