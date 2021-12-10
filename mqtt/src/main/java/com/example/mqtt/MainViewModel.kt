package com.example.mqtt

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MainViewModel(app: Application) : AndroidViewModel(app) {

    @SuppressLint("StaticFieldLeak")
    private val context: Context = app.applicationContext

    private var mqttAndroidClient: MqttAndroidClient =
        MqttAndroidClient(context, MQTT_BASE_ADDRESS, MqttClient.generateClientId())

    private val mqttCallbackExtended = object : MqttCallbackExtended {
        override fun connectionLost(cause: Throwable?) {

        }

        override fun messageArrived(topic: String?, message: MqttMessage?) {

        }

        override fun deliveryComplete(token: IMqttDeliveryToken?) {

        }

        override fun connectComplete(reconnect: Boolean, serverURI: String?) {

        }
    }

    init {
        mqttAndroidClient.setCallback(mqttCallbackExtended)
    }

    fun connectClient(callback: (Boolean, String) -> Unit) {
        try {
            mqttAndroidClient.connect(MqttConnectOptions(), null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    callback(true, "MqttConnection Success")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    exception!!.printStackTrace()
                    callback(false, "MqttConnection Fail")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            callback(false, "MqttException")
        }
    }

    fun subscribe(
        topic: String = DEFAULT_TOPIC,
        qos: Int = 1,
        callback: (Boolean, String) -> Unit
    ) {
        try {
            mqttAndroidClient.subscribe(topic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    callback(true, "MqttSubscribe Success")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    callback(false, "MqttSubscribe Fail")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            callback(false, "MqttException")
        }
    }

    fun publish(
        topic: String = DEFAULT_TOPIC,
        msg: String,
        qos: Int = 1,
        callback: (Boolean, String) -> Unit
    ) {
        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = false
            mqttAndroidClient.publish(topic, message, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    callback(true, "MqttPublish Success")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    callback(false, "MqttPublish Fail")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            callback(false, "MqttException")
        }
    }


    companion object {
        private const val MQTT_BASE_ADDRESS = "tcp://broker.hivemq.com:1883"
        private const val DEFAULT_TOPIC = "mqttExample"
    }
}