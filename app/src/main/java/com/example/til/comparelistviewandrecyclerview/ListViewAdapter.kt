package com.example.til.comparelistviewandrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.til.R

class ListViewAdapter : BaseAdapter() {

    private val itemList = mutableListOf<String>()

    private lateinit var view: View

    fun addAll(list: List<String>) {
        itemList.addAll(list)
    }

    override fun getCount(): Int =
        itemList.size

    override fun getItem(position: Int): String =
        itemList[position]

    override fun getItemId(position: Int): Long =
        position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        if (convertView == null) {
            Log.d("결과", "$position 불림. 생성x")
            view =
                LayoutInflater.from(parent?.context).inflate(R.layout.item_listview, parent, false)

        } else {
            view = convertView
            Log.d("결과", "$position 불림. 생성0")
        }

        val editText: EditText = view.findViewById(R.id.edittext)

        editText.hint = itemList[position]

        return view
    }

}