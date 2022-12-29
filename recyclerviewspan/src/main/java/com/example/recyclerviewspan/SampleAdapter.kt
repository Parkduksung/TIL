package com.example.recyclerviewspan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewspan.databinding.ItemSample2Binding
import com.example.recyclerviewspan.databinding.ItemSampleBinding

class SampleAdapter : RecyclerView.Adapter<SampleViewHolder>() {

    private val list = mutableListOf<String>()

    private lateinit var onClick: () -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(
            ItemSample2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(list[position], onClick, list.size >= 2)
    }

    override fun getItemCount(): Int =
        list.size

    fun addAll(list: List<String>) {
        this.list.addAll(list)
    }

    fun delete() {
        list.removeAt(list.lastIndex)
        notifyDataSetChanged()
    }

    fun setOnClick(listener: () -> Unit) {
        onClick = listener
    }


    fun add() {
        list.add("b")
        notifyDataSetChanged()
    }
}

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(text: String, onClick: () -> Unit)
}

class SampleViewHolder1(private val binding: ItemSampleBinding) :
    BaseViewHolder(binding.root) {

    override fun bind(text: String, onClick: () -> Unit) {
        binding.text.text = text
        itemView.setOnClickListener { onClick() }
    }
}

class SampleViewHolder(private val binding: ItemSample2Binding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String, onClick: () -> Unit, isShowMultiple: Boolean) {

        binding.containerSample.layoutParams.width =
            if (isShowMultiple) itemView.resources.displayMetrics.widthPixels / 2
            else itemView.resources.displayMetrics.widthPixels
        binding.text.text = text

        itemView.setOnClickListener { onClick() }
    }
}