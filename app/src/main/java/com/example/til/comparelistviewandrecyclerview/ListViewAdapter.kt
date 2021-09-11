package com.example.til.comparelistviewandrecyclerview

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.til.R

class ListViewAdapter : BaseAdapter() {

    private val itemList = mutableListOf<String>()

    fun addAll(list: List<String>) {
        itemList.addAll(list)
    }

    override fun getCount(): Int =
        itemList.size

    override fun getItem(position: Int): String =
        itemList[position]

    override fun getItemId(position: Int): Long =
        position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        Log.d("결과", "$position 불림.")

        val layoutInflater =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_listview, parent, false)

        val editText: EditText = layoutInflater.findViewById(R.id.edittext)

        editText.hint = itemList[position]

        return layoutInflater
    }
}