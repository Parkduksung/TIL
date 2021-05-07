package com.example.til.clipboard

import android.app.Service
import android.content.ClipboardManager
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ClipboardService : Service(), ClipboardManager.OnPrimaryClipChangedListener {

    private val clipboard = Clipboard()

    override fun onPrimaryClipChanged() {
        Log.d("결과", clipboard.getClipboardText(this) ?: "null")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onCreate() {
        Log.d("결과", "onCreate")
        clipboard.setClipboardListener(this, this)
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.d("결과", "onDestroy")
        super.onDestroy()
    }
}