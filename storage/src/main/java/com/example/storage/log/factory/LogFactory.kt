package com.example.storage.log.factory

import ErrorLogFactory
import com.example.storage.Level
import com.example.storage.LogOption
import com.example.storage.RLog
import com.example.storage.log.LogType

abstract class LogFactory {

    protected var logOption: LogOption? = null

    open fun print(level: Level, message: String) {
        logOption?.let { option ->
            if (option.printVisibleState) {
                synchronized(this){
                    option.printer.print(RLog.TAG, level, message)
                }
            }
        }
    }

    fun setOption(option: LogOption) {
        this.logOption = option
    }

    companion object {
        inline fun <reified T : LogType> createFactory(option: LogOption): LogFactory =
            when (T::class) {
                LogType.MessageLog::class -> MessageLogFactory().apply { setOption(option) }
                LogType.ErrorLog::class -> ErrorLogFactory().apply { setOption(option) }
                LogType.FormatLog::class -> FormatLogFactory().apply { setOption(option) }
                else -> throw  IllegalArgumentException()
            }
    }
}