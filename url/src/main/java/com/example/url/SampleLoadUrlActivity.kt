package com.example.url

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.url.databinding.ActivitySampleLoadUrlBinding

class SampleLoadUrlActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleLoadUrlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_load_url)
        setContentView(binding.root)

        with(binding) {

            naver.setOnClickListener {
                loadUrl("https://www.naver.com/")
            }

            daum.setOnClickListener {
                loadUrl("https://www.daum.net/")

            }

            google.setOnClickListener {
                loadUrl("https://www.google.com/")
            }

        }
    }

    private fun loadUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}