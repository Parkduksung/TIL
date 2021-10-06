package com.example.sorketclient

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sorketclient.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.InetAddress
import java.net.Socket

class ClientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var socket: Socket

    private lateinit var dataInputStream: DataInputStream

    private lateinit var dataOutputStream: DataOutputStream

    private var isConnected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setContentView(binding.root)

    }

    fun ClientSocketOpen(view: View) {

        Thread {
            try {
                if (
                    binding.ip.text.toString().isNotEmpty() &&
                    binding.port.text.toString().isNotEmpty()
                ) {

                    socket = Socket(
                        InetAddress.getByName(binding.ip.text.toString()),
                        binding.port.text.toString().toInt()
                    )
                    dataInputStream = DataInputStream(socket.getInputStream())
                    dataOutputStream = DataOutputStream(socket.getOutputStream())

                    runOnUiThread {
                        Toast.makeText(this, "Connected With Server", Toast.LENGTH_SHORT).show()
                    }

                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            while (isConnected) {
                try {

                    val msg = dataInputStream.readUTF()

                    runOnUiThread {
                        binding.msg.setText(msg)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
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

    override fun onStop() {
        super.onStop()
        try {
            socket.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}