package com.example.til.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.getSystemService

class Clipboard {
    
    fun getClipboardText(context: Context): String? {
        val clipboardManager = context.getSystemService<ClipboardManager>()
        return clipboardManager?.getClipboardText(context)
    }

    fun setClipboardListener(context: Context, clipChangedListener: ClipboardManager.OnPrimaryClipChangedListener) {
        context.getSystemService<ClipboardManager>()?.addPrimaryClipChangedListener(clipChangedListener)
    }

    fun clearClipboardText(context: Context) {
        val clipboardManager = context.getSystemService<ClipboardManager>()
//        clipboardManager?.
    }

    fun setClipboardTest(context: Context){
        val clipboardManager = context.getSystemService<ClipboardManager>()
        val clipData = ClipData.newPlainText("simple text", "Hello, World!")
        clipboardManager?.setPrimaryClip(clipData)
    }

}


fun ClipboardManager.getClipboardText(context: Context): String? {
    if (hasPrimaryClip()) {
        val clip = primaryClip
        if (clip != null && clip.itemCount > 0) {
            val clipboardText = clip.getItemAt(0).coerceToText(context)
            if (clipboardText != null)
                return clipboardText.toString()
        }
    }
    return null
}

fun ClipboardManager.setClipboardText(context: Context) {
    val clipboardManager = context.getSystemService<ClipboardManager>()
    clipboardManager?.text = null

}