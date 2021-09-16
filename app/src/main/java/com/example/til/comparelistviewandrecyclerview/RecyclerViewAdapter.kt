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

    //여기서 이것을 inner 라고 붙이게 되면 밖에 private 으로 선언된 itemList 를 fun bind 에서 사용할 수 도 있다.
    class RecyclerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
    ) {

        private val editText: EditText = itemView.findViewById(R.id.edittext)

        fun bind(item: String) {
            editText.hint = item
        }

    }

}