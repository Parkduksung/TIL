package com.example.bancapture

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.WindowManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(RegisterActivityLifecycleCallbackImpl.getInstance())
    }

}

class RegisterActivityLifecycleCallbackImpl : Application.ActivityLifecycleCallbacks {

    var toggleBanCapture = false

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        if (toggleBanCapture) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        } else {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    companion object {

        private var INSTANCE: RegisterActivityLifecycleCallbackImpl? = null

        fun getInstance(): RegisterActivityLifecycleCallbackImpl =
            INSTANCE ?: RegisterActivityLifecycleCallbackImpl().also {
                INSTANCE = it
            }
    }

}

