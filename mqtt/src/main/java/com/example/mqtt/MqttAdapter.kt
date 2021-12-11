package com.example.mqtt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class MqttAdapter : RecyclerView.Adapter<BaseMqttViewHolder>() {

    private val mqttMessageList = mutableListOf<MqttMessage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMqttViewHolder {
        return when (viewType) {
            1 -> {
                SendViewHolder(parent, R.layout.item_send)
            }
            2 -> {
                ReceiveViewHolder(parent, R.layout.item_receive)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: BaseMqttViewHolder, position: Int) {
        holder.bind(mqttMessageList[position].message)
    }

    override fun getItemViewType(position: Int): Int =
        mqttMessageList[position].type

    override fun getItemCount(): Int =
        mqttMessageList.size


    fun addMessage(message: MqttMessage) {
        mqttMessageList.add(message)
        notifyItemChanged(mqttMessageList.lastIndex)
    }
}

data class MqttMessage(
    val type: Int,
    val message: String
)


class SendViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    BaseMqttViewHolder(parent, layoutId) {

    override fun bind(item: String) {
        itemView.findViewById<TextView>(R.id.tv_send).text = item
    }
}


class ReceiveViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    BaseMqttViewHolder(parent, layoutId) {

    override fun bind(item: String) {
        itemView.findViewById<TextView>(R.id.tv_receive).text = item
    }
}


abstract class BaseMqttViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {

    abstract fun bind(item: String)
}