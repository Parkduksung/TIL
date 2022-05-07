package com.example.url

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.url.databinding.ActivitySamplePackageBinding

class SamplePackageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySamplePackageBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_package)
        setContentView(binding.root)

        binding.buttonRouteMarket.setOnClickListener {
            if(isPackageInstalled(CHECK_PACKAGE, packageManager)){
                val intent = packageManager.getLaunchIntentForPackage(CHECK_PACKAGE)
                intent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else{
                routeMarketUrl(CHECK_PACKAGE)
            }
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


    /**
     * 현재 단말기에 패키지명으로 된 앱이 설치되어 있는지 확인하는 함수.
     * @param packageName 단말기에 설치되어 있는지 확인하고 싶은 패키지명
     * @param packageManager
     * @return 설치되어 있으면 true / 설치되어있지 않으면 false or Exception 발생시 false
     * 단, Android OS 11 부터 Android Manifest 에 Query 에 추가해줘야 한다.
     * @see https://developer.android.com/about/versions/11/privacy/package-visibility
     */
    private fun isPackageInstalled(packageName: String, packageManager: PackageManager): Boolean {
        return try {
            packageManager.getInstalledPackages(0).any { it.packageName == packageName }
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    companion object {
        private const val CHECK_PACKAGE ="com.kakao.talk"
    }
}