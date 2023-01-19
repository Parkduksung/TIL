package com.example.bancapture

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun captureOk(view: View) {
        RegisterActivityLifecycleCallbackImpl.getInstance().toggleBanCapture = false
        onResume()
    }

    fun captureNo(view: View) {
        RegisterActivityLifecycleCallbackImpl.getInstance().toggleBanCapture = true
        onResume()
    }

    fun routeSub(view: View) {
        startActivity(Intent(this, SubActivity::class.java))
    }


}