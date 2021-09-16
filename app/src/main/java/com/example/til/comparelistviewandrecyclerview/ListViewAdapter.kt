package com.example.til.comparelistviewandrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import com.example.til.R
import com.example.til.comparelistviewandrecyclerview.viewholder.AViewHolder
import com.example.til.comparelistviewandrecyclerview.viewholder.BViewHolder
import com.example.til.comparelistviewandrecyclerview.viewholder.CViewHolder
import com.example.til.comparelistviewandrecyclerview.viewholder.ListViewHolder

class ListViewAdapter : BaseAdapter() {

    private val itemList = mutableListOf<String>()

    private lateinit var view: View

    private var listViewHolder: ListViewHolder? = null

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

            when {

                position % 3 == 0 -> {
                    view =
                        LayoutInflater.from(parent?.context).inflate(R.layout.item_a, parent, false)
                    listViewHolder = AViewHolder(view)
                }

                position % 3 == 1 -> {
                    view =
                        LayoutInflater.from(parent?.context).inflate(R.layout.item_b, parent, false)
                    listViewHolder = BViewHolder(view)

                }

                position % 3 == 2 -> {
                    view =
                        LayoutInflater.from(parent?.context).inflate(R.layout.item_c, parent, false)
                    listViewHolder = CViewHolder(view)
                }

            }

            view.tag = listViewHolder
        } else {
            view = convertView

            listViewHolder = view.tag as? ListViewHolder
        }

        listViewHolder?.bind(itemList[position], position)

        return view
    }



}

