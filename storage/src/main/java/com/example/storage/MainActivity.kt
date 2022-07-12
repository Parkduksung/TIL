package com.example.storage

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.storage.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "RemoteView"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG, "LogTest")
    }
}




//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(binding.root)

//        binding.buttonSearch.setOnClickListener {
//            val file = File(
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absoluteFile,
//                "RemoteViewLog"
//            )
//
//            Log.d("결과", file.exists().toString())
//        }
//
//
//        var mockLogText = ""
//        var count = 1
//
//
//        repeat(4000) {
//            mockLogText += "${count++}"
//        }
//
//        var startCount = 0
//
//        val lastIndex = mockLogText.length / 4000


//        IntRange(0, mockLogText.length / 4000).forEachIndexed { index, i ->
//            if (index == lastIndex) {
//                Log.d("결과", mockLogText.substring((i * 4000), mockLogText.lastIndex + 1))
//            } else {
//                Log.d("결과", mockLogText.substring((i * 4000), (i + 1) * 4000))
//            }
//        }

//        Log.d("결과", "몫 ${mockLogText.length / 4000} 나머지 ${mockLogText.length % 4000}")

//    }
//}