package com.example.recyclerviewspan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.databinding.DataBindingUtil
import com.example.recyclerviewspan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val sampleAdapter = SampleAdapter()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        binding.rvSample.adapter = sampleAdapter
        sampleAdapter.setOnClick {
            sampleAdapter.delete()
        }

        val list = listOf("a","b")

        sampleAdapter.addAll(list)

//        windowManager.defaultDisplay.getMetrics(DisplayMetrics())

    }
}