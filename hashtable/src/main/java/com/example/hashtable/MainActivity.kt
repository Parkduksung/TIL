package com.example.hashtable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //시간초 재는 함수.
        val elapsedTime = measureTimeMillis {

        }
        println(elapsedTime)
    }
}