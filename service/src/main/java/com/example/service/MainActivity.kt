package com.example.service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val canOverlay = registerForActivityResult(OverlayActivityResultContract()) {
        runService()
    }

    private fun runService() {
        if (Settings.canDrawOverlays(this)) {
            startService()
        } else {
            Toast.makeText(this, "canDrawOverlays false", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.button_start).setOnClickListener {
            AlertDialog.Builder(this)
                .setPositiveButton("Ok") { _, _ ->
                    canOverlay.launch("package:$packageName")
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        findViewById<Button>(R.id.button_start).setOnClickListener {
        }

    }

    private fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(OverlayService.newService(this))
        } else {
            startService(OverlayService.newService(this))
        }
    }
}

class OverlayActivityResultContract : ActivityResultContract<String, Boolean>() {
    override fun createIntent(context: Context, input: String?): Intent =
        Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse(input))

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean =
        resultCode == Activity.RESULT_OK
}