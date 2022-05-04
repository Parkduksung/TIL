package com.example.scrollcapture

import android.Manifest
import android.content.Context
import android.os.Build
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

fun Context.checkPermission(callback: (isGranted: Boolean) -> Unit) {
    TedPermission.create()
        .setPermissionListener(object : PermissionListener {
            override fun onPermissionGranted() {
                callback.invoke(true)
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                callback.invoke(false)
            }
        })
        .setPermissions(*providePermissions())
        .setDeniedMessage("If you reject permission")
        .check()
}

fun providePermissions(): Array<String> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    } else {
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }
}
