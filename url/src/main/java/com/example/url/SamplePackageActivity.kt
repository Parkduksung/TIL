package com.example.url

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.url.databinding.ActivitySamplePackageBinding

class SamplePackageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySamplePackageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_package)
        setContentView(binding.root)

        binding.buttonRouteMarket.setOnClickListener {
            routeMarketUrl(packageName)
        }
    }

    /**
     * 페키지명으로 등록되어있는 앱의 마켓 정보를 보여주는 함수.
     * @param packageName 앱의 패키지명.
     */
    private fun routeMarketUrl(packageName: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(
                "https://play.google.com/store/apps/details?id=$packageName"
            )
            setPackage("com.android.vending")
        }
        startActivity(intent)
    }

}