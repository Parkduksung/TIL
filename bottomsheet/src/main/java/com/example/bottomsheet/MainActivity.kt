package com.example.bottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomSheetFragment = BottomSheetFragment()

        findViewById<Button>(R.id.btnClick).setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager, "")
        }
    }
}