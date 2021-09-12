package com.example.til.comparelistviewandrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.til.R
import com.example.til.comparelistviewandrecyclerview.ListViewActivity.Companion.mockItemList

class RecyclerViewActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerview) }

    private val recyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        recyclerView.run {
            adapter = recyclerViewAdapter
        }

        recyclerViewAdapter.addAll(mockItemList)

    }
}