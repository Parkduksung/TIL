package com.example.service

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity(R.layout.activity_sub) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED // 키잠금 해제하기
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD // 화면 켜기
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )
    }
}