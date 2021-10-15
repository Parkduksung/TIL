package com.example.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

fun Context.hasPermission(permission: String): Boolean {

    if (permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION &&
        android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q
    ) {
        return true
    }

    return ActivityCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

fun Activity.requestPermissionWithRationale(
    permission: String,
    requestCode: Int
) {
    val provideRationale = shouldShowRequestPermissionRationale(permission)

    if (provideRationale) {
        Toast.makeText(this, "권한이 수락되었습니다.", Toast.LENGTH_SHORT).show()
    } else {
        requestPermissions(arrayOf(permission), requestCode)
    }
}
