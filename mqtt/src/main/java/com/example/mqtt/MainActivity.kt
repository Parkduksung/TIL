package com.example.mqtt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.connectClient { isSuccess: Boolean, message: String ->
            if(isSuccess) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}