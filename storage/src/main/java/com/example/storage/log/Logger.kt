package com.example.storage.log

import ErrorLog
import com.example.storage.log.factory.FormatLog
import com.example.storage.log.factory.MessageLog

interface Logger : MessageLog, FormatLog, ErrorLog








