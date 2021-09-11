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
            view =
                LayoutInflater.from(parent?.context).inflate(R.layout.item_listview, parent, false)

            listViewHolder = ListViewHolder(view)

            view.tag = listViewHolder
        } else {
            view = convertView

            Log.d("결과", view.tag.toString())

            listViewHolder = view.tag as? ListViewHolder
        }

        listViewHolder?.bind(itemList[position])

        return view
    }

    class ListViewHolder(itemView: View) {

        private val editText: EditText = itemView.findViewById(R.id.edittext)

        fun bind(item: String) {
            editText.hint = item
        }
    }

}