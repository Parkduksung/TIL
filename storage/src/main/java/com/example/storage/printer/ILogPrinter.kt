package com.example.storage.printer

import com.example.storage.Level

interface ILogPrinter {
    var minPrinterLevel: Level

    fun getName(): String
    fun setMinimumPrintLevel(level: Level)
    fun print(tag: String, level: Level, message: String)
}

interface LoggerOption {
    fun setShow(isVisible: Boolean)
    fun setLogPrinter(iLogPrinter: ILogPrinter)
    fun setMinimumPrintLevel(level: Level)
}