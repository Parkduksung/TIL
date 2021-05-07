package com.example.til.clipboard

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