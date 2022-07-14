package com.example.storage.log.factory

import ErrorLogFactory
import com.example.storage.Level
import com.example.storage.RLog
import com.example.storage.log.LogType

abstract class LogFactory {
    open fun print(level: Level, message: String) {
        if (level.ordinal < Level.VERBOSE.ordinal) return

        val traceElements = Throwable().stackTrace

        val (className, lineNumber) = if (traceElements.size > 2) {
            Pair(traceElements[3].fileName, traceElements[3].lineNumber)
        } else {
            Pair("unknown", "-1")
        }
        val log = "(${className}, $lineNumber) $message"

        sortLog(level, log)
    }


    private fun sortLog(level: Level, log: String) {
        when (level) {
            Level.VERBOSE -> {
                android.util.Log.v(RLog.TAG, log)
            }

            Level.DEBUG -> {
                android.util.Log.d(RLog.TAG, log)
            }

            Level.INFO -> {
                android.util.Log.i(RLog.TAG, log)
            }

            Level.WARN -> {
                android.util.Log.w(RLog.TAG, log)
            }

            else -> {
                android.util.Log.e(RLog.TAG, log)
            }
        }
    }


    companion object {
        inline fun <reified T : LogType> createFactory(): LogFactory = when (T::class) {
            LogType.MessageLog::class -> MessageLogFactory()
            LogType.ErrorLog::class -> ErrorLogFactory()
            LogType.FormatLog::class -> FormatLogFactory()
            else -> throw  IllegalArgumentException()
        }
    }
}