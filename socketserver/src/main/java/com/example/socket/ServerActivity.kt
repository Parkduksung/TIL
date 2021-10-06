package com.example.socket

import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.socket.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.UnknownHostException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ServerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var serverSocket: ServerSocket

    private lateinit var socket: Socket

    private lateinit var dataInputStream: DataInputStream

    private lateinit var dataOutputStream: DataOutputStream

    private var isConnected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.ip.setText(getLocalIpAddress())

    }


    fun ServerSocketOpen(view: View) {

        with(binding) {

            if (port.text.toString().isEmpty()) {
                Toast.makeText(this@ServerActivity, "포트번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {

                Thread {

                    try {
                        serverSocket = ServerSocket(port.text.toString().toInt())

                        socket = serverSocket.accept()

                        dataInputStream = DataInputStream(socket.getInputStream())
                        dataOutputStream = DataOutputStream(socket.getOutputStream())

                        runOnUiThread {
                            Toast.makeText(
                                this@ServerActivity,
                                "Connected With Client Socket",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    while (isConnected) {

                        try {

                            val message = dataInputStream.readUTF()

                            msg.setText("[RECV] $message")


                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }.start()
            }
        }
    }


    fun SendMessage(view: View) {
        if (::dataOutputStream.isInitialized) {
            Thread {
                try {
                    dataOutputStream.writeUTF(binding.msg.text.toString())
                    dataOutputStream.flush()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }.start()
        }
    }


    //내 ip 얻기
    @Throws(UnknownHostException::class)
    private fun getLocalIpAddress(): String? {
        val wifiManager = (applicationContext.getSystemService(WIFI_SERVICE) as WifiManager)
        val wifiInfo = wifiManager.connectionInfo
        val ipInt = wifiInfo.ipAddress
        return InetAddress.getByAddress(
            ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array()
        ).hostAddress
    }
}