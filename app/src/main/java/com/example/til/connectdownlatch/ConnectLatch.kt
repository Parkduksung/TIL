package com.example.til.connectdownlatch

import android.util.Log
import java.util.concurrent.CountDownLatch

class ConnectLatch : CountDownLatch(1) {

    fun start() {
        Runnable {
            for( i in 0 .. 10 ){
                Log.d("결과", i.toString())
            }
        }.run()

        this.await()
    }

    fun stop() {
        this.countDown()
    }

}

