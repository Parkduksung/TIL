package com.example.til.stringcomparison

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class StringComparisonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_comparison)

        if("6.0.16.1" >= "6.0.15.21"){
            Log.d("결과", "참")
        }else{
            Log.d("결과", "비교가 안됨.")
        }
    }
}