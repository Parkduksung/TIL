package com.example.network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val networkCheck: NetworkConnection by lazy {
        NetworkConnection(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkCheck.register() // 네트워크 객체 등록
    }

    override fun onDestroy() {
        super.onDestroy()

        networkCheck.unregister() // 네트워크 객체 해제
    }
}