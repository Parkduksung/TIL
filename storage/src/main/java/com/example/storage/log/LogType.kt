package com.example.storage.log

sealed class LogType {
    object MessageLog : LogType()
    object ErrorLog : LogType()
    object FormatLog : LogType()
}
