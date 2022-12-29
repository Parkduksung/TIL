package com.example.til.clipboard

import android.app.Service
import android.content.ClipboardManager
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.til.App

class ClipboardService : Service(), ClipboardManager.OnPrimaryClipChangedListener,
    LifecycleObserver {

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
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        ProcessLifecycleOwner.get().lifecycle.removeObserver(this)
        Log.d("결과", "onDestroy")
        super.onDestroy()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun startBackground() {
        Log.d("결과", "startBackground")
        clipboard.setClipboardTest(App.instance.context())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun startForeground() {
        Log.d("결과", "startForeground")
        Log.d("결과", clipboard.getClipboardText(App.instance.context()) ?: "null")
    }
}