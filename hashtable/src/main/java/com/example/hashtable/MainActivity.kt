package com.example.hashtable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private val hashmap = HashMap<String, Int>()

    private val list = mutableListOf<Pair<String, Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mockList = mutableListOf<Pair<String, Int>>()


        for (i in 0..100000) {
            mockList.add(Pair("$i", i))
        }

        for (i in 0..100000) {
            hashmap["$i"] = i
        }

        list.addAll(mockList)

        //시간초 재는 함수.
        val elapsedTime1 = measureTimeMillis {
            list.addAll(mockList)
            list.distinct()
        }
        Log.d("결과", "결과1 : $elapsedTime1")


        val elapsedTime2 = measureTimeMillis {
            for (i in 0..100000) {
                hashmap["$i"] = i
            }
        }

        Log.d("결과", "결과2 : $elapsedTime2")

        //결론
        //기존에 있는 데이터 위에 동일한 데이터를 추가할 경우 순서와 상관있게 mutableListOf 로 하고 distinct 하였을때보다
        //속도는 hashMap 가 mutableListOf 로 하고 distinct 더 빠르다
        //순서와 상관없이 mutableSetOf 로 했을경우에도 hashMap 이 더 빠르다.
    }
}