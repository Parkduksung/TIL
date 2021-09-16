package com.example.til.comparelistviewandrecyclerview.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.til.R

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