import com.example.storage.Level
import com.example.storage.log.LogType
import com.example.storage.log.factory.LogFactory

class ErrorLogFactory : LogFactory<LogType.ErrorLog>(), ErrorLog {

    override fun v(throwable: Throwable) {
        print(Level.VERBOSE, getStackTraceMessage(throwable))
    }

    override fun d(throwable: Throwable) {
        print(Level.DEBUG, getStackTraceMessage(throwable))
    }

    override fun i(throwable: Throwable) {
        print(Level.INFO, getStackTraceMessage(throwable))
    }

    override fun w(throwable: Throwable) {
        print(Level.WARN, getStackTraceMessage(throwable))
    }

    override fun e(throwable: Throwable) {
        print(Level.ERROR, getStackTraceMessage(throwable))
    }

    override fun r(throwable: Throwable) {
        print(Level.REPORT, getStackTraceMessage(throwable))
    }

    override fun wtf(throwable: Throwable) {
        print(Level.WTF, getStackTraceMessage(throwable))
    }

    private fun getStackTraceMessage(throwable: Throwable): String =
        android.util.Log.getStackTraceString(throwable)
}


interface ErrorLog {
    fun v(throwable: Throwable)
    fun d(throwable: Throwable)
    fun i(throwable: Throwable)
    fun w(throwable: Throwable)
    fun e(throwable: Throwable)
    fun r(throwable: Throwable)
    fun wtf(throwable: Throwable)
}