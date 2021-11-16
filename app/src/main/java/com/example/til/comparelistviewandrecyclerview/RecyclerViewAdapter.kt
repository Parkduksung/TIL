package com.example.til.comparelistviewandrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.til.R

class RecyclerViewAdapter : RecyclerView.Adapter<BaseRecyclerViewHolder<Any>>() {

    private val itemList = mutableListOf<String>()

    fun addAll(list: List<String>) {
        itemList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Any> {
        Log.d("결과", "여기는 onCreateViewHolder")
        return sortViewHolder(parent, viewType)
    }



    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<Any>, position: Int) {
        Log.d("결과", "여기는 onBindViewHolder")
        when (holder) {
            is ARecyclerViewHolder -> {
                holder.bind(itemList[position])
            }
            is BRecyclerViewHolder -> {
                holder.bind(itemList[position])
            }
            is CRecyclerViewHolder -> {
                holder.bind(itemList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("결과", "여기는 getItemCount")
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("결과", "여기는 getItemViewType")
        return super.getItemViewType(position)
    }

    override fun getItemId(position: Int): Long {
        Log.d("결과", "여기는 getItemId")
        return super.getItemId(position)
    }

    companion object {

        fun sortViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Any> {

            Log.d("결과", viewType.toString())

            return when (viewType) {
                0 -> {
                    ARecyclerViewHolder(parent, R.layout.item_a)
                }
                1 -> {
                    BRecyclerViewHolder(parent, R.layout.item_b)
                }
                2 -> {
                    CRecyclerViewHolder(parent, R.layout.item_c)
                }
                else -> {
                    throw  IllegalArgumentException()
                }
            }
        }
    }

}


abstract class BaseRecyclerViewHolder<T>(parent: ViewGroup, @LayoutRes layoutId: Int) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {

    abstract fun bind(item: T)
}


class ARecyclerViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    BaseRecyclerViewHolder<Any>(parent, layoutId) {

    private val textView: TextView = itemView.findViewById(R.id.textview)

    private val editText: EditText = itemView.findViewById(R.id.edittext)

    override fun bind(item: Any) {

        textView.text = "$adapterPosition 번째 A"

        editText.hint = (item as String)
    }

}


class BRecyclerViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    BaseRecyclerViewHolder<Any>(parent, layoutId) {

    private val textView: TextView = itemView.findViewById(R.id.textview)

    private val editText: EditText = itemView.findViewById(R.id.edittext)

    override fun bind(item: Any) {

        textView.text = "$adapterPosition 번째 B"

        editText.hint = item as String
    }

}

class CRecyclerViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    BaseRecyclerViewHolder<Any>(parent, layoutId) {

    private val textView: TextView = itemView.findViewById(R.id.textview)

    private val editText: EditText = itemView.findViewById(R.id.edittext)

    override fun bind(item: Any) {

        textView.text = "$adapterPosition 번째 C"

        editText.hint = item as String
    }

}



