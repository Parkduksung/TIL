package com.example.service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity(R.layout.activity_sub) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.addFlags(
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        Handler(Looper.getMainLooper()).postDelayed(
            { Toast.makeText(this, "여기", Toast.LENGTH_SHORT).show() }, 2000L
        )
    }

    private fun createMediaProjection() {
        val mediaProjectionManager =
            getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        val result: ActivityResultLauncher<Intent> =
            registerForActivityResult(MediaProjectionResultContract()) {
                sendBroadcast(it)
                finish()
            }
        result.launch(mediaProjectionManager.createScreenCaptureIntent())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val uri = Uri.fromParts("package", packageName, null)
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
                startActivityForResult(intent, 0)
            } else {
                val intent = Intent(applicationContext, LockScreenService::class.java)
                startForegroundService(intent)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "해라", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(applicationContext, LockScreenService::class.java)
                startForegroundService(intent)
            }
        }
    }
}

internal class MediaProjectionResultContract : ActivityResultContract<Intent, Intent>() {

    override fun createIntent(context: Context, input: Intent?): Intent =
        input!!

    override fun parseResult(resultCode: Int, intent: Intent?): Intent? {
        return if (resultCode == Activity.RESULT_OK && intent != null) {
            Log.d("결과", "여기 ok")
            null
        } else {
            Log.d("결과", "여기 no")
            null
        }
    }
}