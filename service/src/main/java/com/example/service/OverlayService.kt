package com.example.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class OverlayService : Service() {

    companion object {
        fun newService(context: Context): Intent =
            Intent(context, OverlayService::class.java)

        private const val NOTIFICATION_DOWNLOAD_ID = 1
        private const val NOTIFICATION_COMPLETE_ID = 2
        private const val CHANNEL_ID = "my_channel"
        private const val CHANNEL_NAME = "default"
        private const val CHANNEL_DESCRIPTION = "This is default notification channel"
    }

    private val notificationManager
        get() = getSystemService(NOTIFICATION_SERVICE) as NotificationManager


    private val receiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent != null) {
                when(intent.action) {
                    Intent.ACTION_SCREEN_OFF -> {
                        val newIntent = Intent(context, LockScreenActivity::class.java)
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(newIntent)
                    }
                }
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? =
        null


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_DOWNLOAD_ID, createDownloadingNotification(0))
        thread {
            val filter = IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF)

            for (i in 1..100) {
                Thread.sleep(100)
                updateProgress(i)
            }
            stopForeground(true)
            stopSelf()
            notificationManager.notify(NOTIFICATION_COMPLETE_ID, createCompleteNotification())
            registerReceiver(receiver, filter)
        }
        return START_STICKY
    }

    private fun createCompleteNotification() = NotificationCompat.Builder(this, CHANNEL_ID).apply {
        setContentTitle("Download complete!")
        setContentText("Nice 🚀")
        setSmallIcon(R.drawable.ic_launcher_background)
        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        val intent = Intent(this@OverlayService , SubActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }.build()

    private fun updateProgress(@androidx.annotation.IntRange(from = 0L, to = 100L) progress: Int) {
        notificationManager.notify(
            NOTIFICATION_DOWNLOAD_ID,
            createDownloadingNotification(progress)
        )
    }

    override fun onCreate() {
        super.onCreate()
        registerDefaultNotificationChannel()
    }

    private fun registerDefaultNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(createDefaultNotificationChannel())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createDefaultNotificationChannel() =
        NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW).apply {
            description = CHANNEL_DESCRIPTION
            this.setShowBadge(true)
            this.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
        }

    private fun createDownloadingNotification(
        @androidx.annotation.IntRange(
            from = 0L,
            to = 100L
        ) progress: Int
    ) =
        NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle("Download video...")
            setContentText("Wait!")
            setSmallIcon(R.drawable.ic_launcher_background)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            setContentIntent(
                PendingIntent.getActivity(
                    this@OverlayService,
                    0,
                    Intent(this@OverlayService, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    },
                    0
                )
            )

            setProgress(100, progress, false)
        }.build()

    override fun onDestroy() {
        super.onDestroy()
        Log.d("결과", "onDestroy")
    }

}