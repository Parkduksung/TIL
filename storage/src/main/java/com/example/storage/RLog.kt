package com.example.storage

import ErrorLog
import com.example.storage.log.factory.LogFactory
import com.example.storage.log.LogType
import com.example.storage.log.Logger
import com.example.storage.log.factory.FormatLog
import com.example.storage.log.factory.MessageLog

object RLog : Logger {

    var TAG = "RV6"

    var MIN_PRINT_LEVEL = Level.VERBOSE

    /**
     * Print Log Message
     */
    override fun v(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>() as MessageLog).v(message)
    }

    override fun d(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>() as MessageLog).d(message)
    }

    override fun i(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>() as MessageLog).i(message)
    }

    override fun w(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>() as MessageLog).w(message)
    }

    override fun e(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>() as MessageLog).e(message)
    }

    override fun r(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>() as MessageLog).r(message)
    }

    override fun wtf(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>() as MessageLog).wtf(message)
    }


    /**
     * Print Log Exception
     */
    override fun v(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>() as ErrorLog).v(throwable)
    }

    override fun d(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>() as ErrorLog).d(throwable)
    }

    override fun i(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>() as ErrorLog).i(throwable)
    }

    override fun w(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>() as ErrorLog).w(throwable)
    }

    override fun e(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>() as ErrorLog).e(throwable)
    }

    override fun r(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>() as ErrorLog).r(throwable)
    }

    override fun wtf(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>() as ErrorLog).wtf(throwable)
    }


    /**
     * Print Log Format message
     */
    override fun v(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>() as FormatLog).v(format, args)
    }

    override fun d(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>() as FormatLog).d(format, args)
    }

    override fun i(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>() as FormatLog).i(format, args)
    }

    override fun w(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>() as FormatLog).w(format, args)
    }

    override fun e(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>() as FormatLog).e(format, args)
    }

    override fun r(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>() as FormatLog).r(format, args)
    }

    override fun wtf(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>() as FormatLog).wtf(format, args)
    }
}

enum class Level(val keyword: String) {
    VERBOSE("V"),
    DEBUG("D"),
    INFO("I"),
    WARN("W"),
    ERROR("E"),
    ASSERT("A"),
    REPORT("R"),
    WTF("F")
}


