package com.example.storage.log.factory

import com.example.storage.Level
import java.lang.StringBuilder
import java.util.*

class FormatLogFactory : LogFactory(), FormatLog {

    private val logStringBuilder = StringBuilder()
    private val logFormatter = Formatter(logStringBuilder, Locale.getDefault())

    override fun v(format: String, vararg args: Any) {
        print(Level.VERBOSE, format(format, args))
    }

    override fun d(format: String, vararg args: Any) {
        print(Level.DEBUG, format(format, args))
    }

    override fun i(format: String, vararg args: Any) {
        print(Level.INFO, format(format, args))
    }

    override fun w(format: String, vararg args: Any) {
        print(Level.WARN, format(format, args))
    }

    override fun e(format: String, vararg args: Any) {
        print(Level.ERROR, format(format, args))
    }

    override fun r(format: String, vararg args: Any) {
        print(Level.REPORT, format(format, args))
    }

    override fun wtf(format: String, vararg args: Any) {
        print(Level.WTF, format(format, args))
    }

    private fun format(format: String, vararg args: Any): String {
        val toFormat = logFormatter.format(format, args).toString()
        logStringBuilder.clear()
        return toFormat
    }
}


interface FormatLog {
    fun v(format: String, vararg args: Any)
    fun d(format: String, vararg args: Any)
    fun i(format: String, vararg args: Any)
    fun w(format: String, vararg args: Any)
    fun e(format: String, vararg args: Any)
    fun r(format: String, vararg args: Any)
    fun wtf(format: String, vararg args: Any)
}

