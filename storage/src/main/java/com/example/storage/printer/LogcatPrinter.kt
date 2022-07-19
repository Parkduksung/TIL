package com.example.storage.printer

import com.example.storage.Level
import com.example.storage.RLog

class LogcatPrinter : ILogPrinter {

    override var minPrinterLevel: Level = Level.VERBOSE

    override fun getName(): String {
        return NAME
    }

    override fun setMinimumPrintLevel(level: Level) {
        minPrinterLevel = level
    }


    override fun print(tag: String, level: Level, message: String) {
        if (level.ordinal < RLog.MIN_PRINT_LEVEL.ordinal) return

        val traceElements = Throwable().stackTrace

        val (className, lineNumber) = if (traceElements.size > 2) {
            Pair(traceElements[3].fileName, traceElements[3].lineNumber)
        } else {
            Pair("unknown", "-1")
        }
        val log = "(${className}, $lineNumber) $message"

        sortLog(tag, level, log)
    }


    private fun sortLog(tag: String, level: Level, log: String) {
        when (level) {
            Level.VERBOSE -> {
                android.util.Log.v(tag, log)
            }

            Level.DEBUG -> {
                android.util.Log.d(tag, log)
            }

            Level.INFO -> {
                android.util.Log.i(tag, log)
            }

            Level.WARN -> {
                android.util.Log.w(tag, log)
            }

            else -> {
                android.util.Log.e(tag, log)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other != null) && (other is ILogPrinter) && (getName() == other.getName())
    }

    override fun hashCode(): Int {
        return getName().hashCode()
    }

    companion object {
        private const val NAME = "LogcatPrinter"
    }
}