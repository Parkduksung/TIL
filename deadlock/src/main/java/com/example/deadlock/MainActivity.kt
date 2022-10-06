package com.example.deadlock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("결과,", "start test of coroutines")
        runBlocking {
            val job = launch {
                runBlocking {
                    println("await cancellation")
                    awaitCancellation()
                }
            }
            Log.d("결과,","launched job")
            delay(100)
            Log.d("결과,","waited a bit")
            job.cancelAndJoin()
            Log.d("결과,","canceled and joined")
        }
        Log.d("결과,", "end test of coroutines")
    }
}