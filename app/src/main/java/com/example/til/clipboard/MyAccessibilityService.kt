package com.example.til.clipboard

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat

class MyAccessibilityService : AccessibilityService() {

    private val clipboard = Clipboard()

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val source: AccessibilityNodeInfo? = event?.source
        if (source != null) {
            val rowNode: AccessibilityNodeInfo? = rootInActiveWindow
            if (rowNode != null) {
                for (i in 0 until rowNode.childCount) {
                    val accessibilityNodeInfo: AccessibilityNodeInfo = rowNode.getChild(i)
                    if (accessibilityNodeInfo.isEditable && accessibilityNodeInfo.isFocused) {
                        accessibilityNodeInfo.performAction(AccessibilityNodeInfoCompat.ACTION_PASTE)
                        return
                    }
                }
            }
        }
    }

    override fun onInterrupt() {

    }

    override fun onCreate() {
        super.onCreate()
        clipboard.setClipboardListener(this) {
            Log.d("결과", "여기찍힘?")
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("결과","연결됨")
    }
}