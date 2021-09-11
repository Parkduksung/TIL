package com.example.til.comparelistviewandrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
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

    abstract class ListViewHolder {

        abstract fun bind(item: String, position: Int)
    }


    class AViewHolder(itemView: View) : ListViewHolder() {

        private val editText: EditText = itemView.findViewById(R.id.edittext)

        private val textView: TextView = itemView.findViewById(R.id.textview)


        @SuppressLint("SetTextI18n")
        override fun bind(item: String, position: Int) {

            textView.text = "$position 번째 A"

            editText.hint = item

        }

    }

    class BViewHolder(itemView: View) : ListViewHolder() {

        private val editText: EditText = itemView.findViewById(R.id.edittext)

        private val textView: TextView = itemView.findViewById(R.id.textview)


        @SuppressLint("SetTextI18n")
        override fun bind(item: String, position: Int) {

            textView.text = "$position 번째 B"

            editText.hint = item

        }

    }

    class CViewHolder(itemView: View) : ListViewHolder() {

        private val editText: EditText = itemView.findViewById(R.id.edittext)

        private val textView: TextView = itemView.findViewById(R.id.textview)


        @SuppressLint("SetTextI18n")
        override fun bind(item: String, position: Int) {

            textView.text = "$position 번째 C"

            editText.hint = item

        }

    }

}