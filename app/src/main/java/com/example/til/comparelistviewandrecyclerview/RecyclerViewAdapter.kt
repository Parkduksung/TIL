package com.example.til.comparelistviewandrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.til.R

class RecyclerViewAdapter : RecyclerView.Adapter<BaseRecyclerViewHolder>() {

    private val itemList = mutableListOf<String>()

    fun addAll(list: List<String>) {
        itemList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(parent, R.layout.item_recyclerview)

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        (holder as RecyclerViewHolder).bind(itemList[position])
    }

    override fun getItemCount(): Int =
        itemList.size

}


abstract class BaseRecyclerViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    )


class RecyclerViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    BaseRecyclerViewHolder(parent, layoutId) {

    private val editText: EditText = itemView.findViewById(R.id.edittext)

    fun bind(item: String) {
        editText.hint = item
    }

}