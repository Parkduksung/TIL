package com.example.deadlock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val MIN_FRAME_INTERVAL: Long = 1000 / 20

    private var sampleThread: SampleThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.btn_start).setOnClickListener {
            sampleThread = SampleThread().apply {
                start()
                Global.setThread(this)
                SampleRunnable().run()
            }
        }


        findViewById<Button>(R.id.btn_interrupt).setOnClickListener {
            release()
        }

    }

    @Synchronized
    private fun release() {
        if (sampleThread?.isAlive == true) {
            Log.d("결과", "여기탐?")
            sampleThread?.interrupt()
            sampleThread?.join()
            sampleThread = null
        }
    }

    override fun onStop() {
        super.onStop()
        release()
    }

    inner class SampleThread : Thread() {

        override fun run() {
            Log.d("결과", "SampleThread Run")

            try {
                while (!isInterrupted) {
                    synchronized(this@MainActivity) {


                    }

                    sleep(MIN_FRAME_INTERVAL)
                }
            } catch (e: InterruptedException) {
                Log.d("결과", "SampleThread InterruptedException")
            }
            Log.d("결과", "SampleThread Terminate")
        }
    }


    inner class SampleRunnable : java.lang.Runnable {

        override fun run() {
            while (Global.getThread()?.isInterrupted == true) {
                synchronized(this@MainActivity) {
                    Log.d("결과", "SampleRunnable run")
                }
            }
        }
    }

}

object Global {

    private var sampleThread: MainActivity.SampleThread? = null

    fun setThread(thread: MainActivity.SampleThread) {
        Log.d("결과", "Global setThread")
        sampleThread = thread
    }

    fun getThread(): MainActivity.SampleThread? = sampleThread

}

