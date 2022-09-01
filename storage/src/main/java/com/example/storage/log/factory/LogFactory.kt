package com.example.storage.log.factory

import ErrorLogFactory
import com.example.storage.Level
import com.example.storage.LogOption
import com.example.storage.RLog
import com.example.storage.log.LogType
import com.example.storage.log.Logger

abstract class LogFactor{

    protected var logOption: LogOption? = null

    open fun print(level: Level, message: String) {
        logOption?.let { option ->
            if (option.printVisibleState) {
                synchronized(this) {
                    option.printer.print(RLog.TAG, level, message)
                }
            }
        }
    }

    fun setOption(option: LogOption) {
        this.logOption = option
    }

//    //자식에서 생성해서 추상화하는것으로 수정해볼것.
//    companion object {
//        inline fun <reified T : LogType> createFactory(option: LogOption): LogFactory =
//            when (T::class) {
//                LogType.MessageLog::class -> MessageLogFactory().apply { setOption(option) }
//                LogType.ErrorLog::class -> ErrorLogFactory().apply { setOption(option) }
//                LogType.FormatLog::class -> FormatLogFactory().apply { setOption(option) }
//                else -> throw  IllegalArgumentException()
//            }
//    }
}


interface Sample<in T : LogType> {
    fun setSample(item: T)
}

class Sample1 : Sample<LogType.ErrorLog> {
    override fun setSample(item: LogType.ErrorLog) {

    }
}



abstract class LogFactory1 {


    abstract fun makeLog(): Logger

    companion object {
        inline fun <reified T : Logger> createFactory(): LogFactory1 = when (T::class) {
            MessageLog1::class -> MessageLogFactory1()
            else -> throw IllegalArgumentException()
        }
    }
}

class Printer(private val stringFormatterStrategy: (String) -> String) {

    fun printString(string: String) {
        println(stringFormatterStrategy(string))
    }

}

val upperCaseFormatter = { it: String -> it.toUpperCase() }


class MessageLog1 : MessageLog {

    override fun v(message: String) {
        TODO("Not yet implemented")
    }

    override fun d(message: String) {
        TODO("Not yet implemented")
    }

    override fun i(message: String) {
        TODO("Not yet implemented")
    }

    override fun w(message: String) {
        TODO("Not yet implemented")
    }

    override fun e(message: String) {
        TODO("Not yet implemented")
    }

    override fun r(message: String) {
        TODO("Not yet implemented")
    }

    override fun wtf(message: String) {
        TODO("Not yet implemented")
    }
}


class MessageLogFactory1 : LogFactory1() {
    override fun makeLog(): Logger  = MessageLogFactory() as MessageLog
}