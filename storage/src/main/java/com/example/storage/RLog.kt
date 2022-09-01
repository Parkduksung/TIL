package com.example.storage

import ErrorLog
import android.os.Environment
import com.example.storage.log.LogType
import com.example.storage.log.Logger
import com.example.storage.log.factory.*
import com.example.storage.printer.ILogPrinter
import com.example.storage.printer.LogcatPrinter
import com.example.storage.printer.LoggerOption
import org.jetbrains.annotations.NotNull
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


//Log 의 기능을 확장하므로 Adapter 패턴도 고려해볼 수 있다.
object RLog : Logger, LoggerOption {

    var TAG = "RV6"
    var MIN_PRINT_LEVEL = Level.VERBOSE

    private var logOption = LogOption()

    /**
     * 기존에 있는 로그 프린터들 사용 가능하게 적용.
     */
    override fun setLogPrinter(@NotNull iLogPrinter: ILogPrinter) {
        logOption = logOption.copy(printer = iLogPrinter)
    }

    override fun setShow(isVisible: Boolean) {
        logOption = logOption.copy(printVisibleState = isVisible)
    }

    override fun setMinimumPrintLevel(level: Level) {
        MIN_PRINT_LEVEL = level
    }


    /**
     * Print Log Message
     */
    override fun v(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>(logOption) as MessageLog).v(message)
    }

    override fun d(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>(logOption) as MessageLog).d(message)
    }

    override fun i(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>(logOption) as MessageLog).i(message)
    }

    override fun w(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>(logOption) as MessageLog).w(message)
    }

    override fun e(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>(logOption) as MessageLog).e(message)
    }

    override fun r(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>(logOption) as MessageLog).r(message)
    }

    override fun wtf(message: String) {
        (LogFactory.createFactory<LogType.MessageLog>(logOption) as MessageLog).wtf(message)
    }


    /**
     * Print Log Exception
     */
    override fun v(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>(logOption) as ErrorLog).v(throwable)
    }

    override fun d(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>(logOption) as ErrorLog).d(throwable)
    }

    override fun i(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>(logOption) as ErrorLog).i(throwable)
    }

    override fun w(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>(logOption) as ErrorLog).w(throwable)
    }

    override fun e(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>(logOption) as ErrorLog).e(throwable)
    }

    override fun r(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>(logOption) as ErrorLog).r(throwable)
    }

    override fun wtf(throwable: Throwable) {
        (LogFactory.createFactory<LogType.ErrorLog>(logOption) as ErrorLog).wtf(throwable)
    }


    /**
     * Print Log Format message
     */
    override fun v(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>(logOption) as FormatLog).v(format, args)
    }

    override fun d(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>(logOption) as FormatLog).d(format, args)
    }

    override fun i(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>(logOption) as FormatLog).i(format, args)
    }

    override fun w(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>(logOption) as FormatLog).w(format, args)
    }

    override fun e(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>(logOption) as FormatLog).e(format, args)
    }

    override fun r(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>(logOption) as FormatLog).r(format, args)
    }

    override fun wtf(format: String, vararg args: Any) {
        (LogFactory.createFactory<LogType.FormatLog>(logOption) as FormatLog).wtf(format, args)
    }


    /**
     * 이 부분을 interface 로 빼야하는지 고민.
     */
    private var fullLogProcess: Process? = null
    private var filteredLogProcess: Process? = null

    fun saveLog(callback: (isSave: Boolean) -> Unit) {
        if (fullLogProcess == null && filteredLogProcess == null) {
            try {
                var sdf = SimpleDateFormat("yyyy-MM-dd")
                val now = Date()
                val extDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

                val logDir = File(extDir, "RemoteViewLog")
                val todayDir = File(logDir, sdf.format(now))
                if (!todayDir.exists() || !todayDir.isDirectory) {
                    todayDir.mkdirs()
                }

                d("Log dir: " + todayDir.absolutePath)

                sdf = SimpleDateFormat("HH_mm_ss")
                val fullLogFile =
                    String.format("%s/%s_full.log", todayDir.absolutePath, sdf.format(now))
                val filteredFile =
                    String.format("%s/%s_filtered.log", todayDir.absolutePath, sdf.format(now))


                //단말에 따라 (혹은 버전에 따라?) 필터를 주지 않아도 자기 프로세스의 로그만 접근할 수 있는 경우가 있음.
                fullLogProcess = Runtime.getRuntime()
                    .exec(arrayOf("logcat", "-v", "threadtime", "-f", fullLogFile))
                filteredLogProcess = Runtime.getRuntime().exec(
                    arrayOf(
                        "logcat", "-s", "-v", "threadtime", "-f",
                        filteredFile, TAG
                    )
                )
                callback(true)
            } catch (e: Exception) {
                e(e.message.orEmpty())
                callback(false)
            }
        } else {
            d("Logging process is running..")
        }
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

data class LogOption(
    var type: LogType = LogType.MessageLog,
    var printer: ILogPrinter = LogcatPrinter(),
    var printVisibleState: Boolean = true,
    var isLargeMessage: Boolean = false
)


class LogAdapter(var option: LogOption, var printer: ILogPrinter) : Logger {

    private val messageLogFactory = MessageLogFactory()


    override fun v(throwable: Throwable) {

    }

    override fun d(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override fun i(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override fun w(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override fun e(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override fun r(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override fun wtf(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override fun v(format: String, vararg args: Any) {
        TODO("Not yet implemented")
    }

    override fun d(format: String, vararg args: Any) {
        TODO("Not yet implemented")
    }

    override fun i(format: String, vararg args: Any) {
        TODO("Not yet implemented")
    }

    override fun w(format: String, vararg args: Any) {
        TODO("Not yet implemented")
    }

    override fun e(format: String, vararg args: Any) {
        TODO("Not yet implemented")
    }

    override fun r(format: String, vararg args: Any) {
        TODO("Not yet implemented")
    }

    override fun wtf(format: String, vararg args: Any) {
        TODO("Not yet implemented")
    }


    override fun v(message: String) {
        messageLogFactory.v(message)
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

