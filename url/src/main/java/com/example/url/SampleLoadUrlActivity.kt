package com.example.url

import android.app.AlertDialog
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

//            naver.setOnClickListener {
//                loadUrl("https://www.naver.com/")
//            }

            google.setOnClickListener {
                AlertDialog.Builder(this@SampleLoadUrlActivity)
                    .setTitle("예약확인")
                    .setMessage("에약된 상상랩번호, 날짜, 시간은 상상~~~~~ 입니다.")
                    .create()
                    .show()
            }
//
//            google.setOnClickListener {
//                loadUrl("https://www.google.com/")
//            }

        }
    }

    private fun loadUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}