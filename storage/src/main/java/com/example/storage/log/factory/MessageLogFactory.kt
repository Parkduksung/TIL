package com.example.storage.log.factory

import com.example.storage.Level

class MessageLogFactory : LogFactory(), MessageLog {

    override fun v(message: String) {
        print(Level.VERBOSE, message)
    }

    override fun d(message: String) {
        print(Level.DEBUG, message)
    }

    override fun i(message: String) {
        print(Level.INFO, message)
    }

    override fun w(message: String) {
        print(Level.WARN, message)
    }

    override fun e(message: String) {
        print(Level.ERROR, message)
    }

    override fun r(message: String) {
        print(Level.REPORT, message)
    }

    override fun wtf(message: String) {
        print(Level.WTF, message)
    }
}

interface MessageLog {
    fun v(message: String)
    fun d(message: String)
    fun i(message: String)
    fun w(message: String)
    fun e(message: String)
    fun r(message: String)
    fun wtf(message: String)
}