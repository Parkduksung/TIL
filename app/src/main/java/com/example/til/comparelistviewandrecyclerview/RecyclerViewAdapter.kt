package com.example.til.comparelistviewandrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.til.R

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    private val itemList = mutableListOf<String>()

    fun addAll(list: List<String>) {
        itemList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int =
        itemList.size

    class RecyclerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
    ) {

        private val editText: EditText = itemView.findViewById(R.id.edittext)

        fun bind(item: String) {
            editText.hint = item
        }

    }

}