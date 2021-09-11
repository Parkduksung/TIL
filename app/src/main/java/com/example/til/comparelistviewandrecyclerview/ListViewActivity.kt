package com.example.til.comparelistviewandrecyclerview

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class ListViewActivity : AppCompatActivity() {

    private val listview: ListView by lazy { findViewById(R.id.listview) }

    private val listViewAdapter = ListViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)


        listview.run {
            adapter = listViewAdapter
        }

        listViewAdapter.addAll(mockItemList)
    }

    companion object {
        val mockItemList = mutableListOf<String>().apply {
            for (i in 0..30) {
                add("---$i 번째 힌트입니다.---")
            }
        }
    }
}