package com.example.mqtt

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.MqttMessage

class MainViewModel(app: Application) : AndroidViewModel(app) {

    @SuppressLint("StaticFieldLeak")
    private val context: Context = app.applicationContext

    private var mqttAndroidClient: MqttAndroidClient =
        MqttAndroidClient(context, MQTT_BASE_ADDRESS, MqttClient.generateClientId())

    val inputEditText = MutableLiveData<String>()

    private val _mainViewState = MutableLiveData<MainViewState>()
    val mainViewState: LiveData<MainViewState> = _mainViewState

    private val mqttCallbackExtended = object : MqttCallbackExtended {
        override fun connectionLost(cause: Throwable?) {

        }

        override fun messageArrived(
            topic: String?,
            message: MqttMessage?
        ) {
            if (!isSend) {
                _mainViewState.value =
                    MainViewState.ReceiveMessage(
                        MqttMessage(type = 2, message = message.toString())
                    )
            }
        }

        override fun deliveryComplete(token: IMqttDeliveryToken?) {

        }

        override fun connectComplete(reconnect: Boolean, serverURI: String?) {

        }
    }

    init {
        mqttAndroidClient.setCallback(mqttCallbackExtended)
    }

    private var isSend = false

    fun connectClient() {
        try {
            mqttAndroidClient.connect(MqttConnectOptions(), null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    _mainViewState.value = MainViewState.Connection
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    exception!!.printStackTrace()
                    _mainViewState.value = MainViewState.Error(message = "MqttConnection Fail")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            _mainViewState.value = MainViewState.Error(message = "MqttException")
        }
    }

    fun subscribe(
        topic: String = DEFAULT_TOPIC,
        qos: Int = DEFAULT_QOS
    ) {
        try {
            mqttAndroidClient.subscribe(topic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    _mainViewState.value = MainViewState.Subscribe
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    _mainViewState.value = MainViewState.Error(message = "MqttSubscribe Fail")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            _mainViewState.value = MainViewState.Error(message = "MqttException")
        }
    }

    fun publish() {
        inputEditText.value?.let { msg ->
            try {
                isSend = true
                val message = MqttMessage()
                message.payload = msg.toByteArray()
                message.qos = DEFAULT_QOS
                message.isRetained = false
                mqttAndroidClient.publish(
                    DEFAULT_TOPIC,
                    message,
                    null,
                    object : IMqttActionListener {
                        override fun onSuccess(asyncActionToken: IMqttToken?) {
                            isSend = false
                            _mainViewState.value =
                                MainViewState.SendMessage(MqttMessage(type = 1, msg))
                        }

                        override fun onFailure(
                            asyncActionToken: IMqttToken?,
                            exception: Throwable?
                        ) {
                            _mainViewState.value = MainViewState.Error(message = "MqttPublish Fail")
                            isSend = false
                        }
                    })
            } catch (e: MqttException) {
                e.printStackTrace()
                _mainViewState.value = MainViewState.Error(message = "MqttException")
                isSend = false
            }
        }
    }


    sealed class MainViewState {
        object Connection : MainViewState()
        object Subscribe : MainViewState()
        data class Error(val message: String) : MainViewState()
        data class SendMessage(val mqttMessage: com.example.mqtt.MqttMessage) : MainViewState()
        data class ReceiveMessage(val mqttMessage: com.example.mqtt.MqttMessage) : MainViewState()
    }

    companion object {
        private const val MQTT_BASE_ADDRESS = "tcp://broker.hivemq.com:1883"
        private const val DEFAULT_TOPIC = "mqttExample"
        private const val DEFAULT_QOS = 1
    }
}