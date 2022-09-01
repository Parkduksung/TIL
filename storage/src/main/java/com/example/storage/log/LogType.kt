package com.example.storage.log

import com.example.storage.log.factory.Log

sealed class LogType : Log {
    object MessageLog : LogType()
    object ErrorLog : LogType()
    object FormatLog : LogType()
}
