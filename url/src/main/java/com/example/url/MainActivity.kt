package com.example.url

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testUrl1()
    }

    private fun testUrl1() {
        Thread {
            val urlText = "https://jsonplaceholder.typicode.com/users?id=6"
            val url = URL(urlText)
            val netConn = url.openConnection() as HttpURLConnection

            if (netConn.responseCode == HttpURLConnection.HTTP_OK) {
                val streamReader = InputStreamReader(netConn.inputStream)
                val buffered = BufferedReader(streamReader)

                val content = StringBuilder()

                while (true) {
                    val line = buffered.readLine() ?: break
                    content.append(line)
                }
                buffered.close()
                netConn.disconnect()

                runOnUiThread {
                    Log.d("결과", content.toString())
                }
            }
        }.start()


    }

    private fun testUrl2() {

        Thread {
            val urlText = "https://jsonplaceholder.typicode.com/users?id=6"
            val url = URL(urlText)
            val netConn = url.openConnection() as HttpURLConnection

            if (netConn.responseCode == HttpURLConnection.HTTP_OK) {
                val streamReader = InputStreamReader(netConn.inputStream)
                val buffered = BufferedReader(streamReader)

                val content = StringBuilder()

                //BufferedReader 의 readLine 의 기능때문에 문제가 되었던거였다.
                while (true) {
                    val line = buffered.readLine()
                    if (line != null) {
                        val line1 = buffered.readLine()
                        content.append(line1)
                    } else {
                        break
                    }
                }

                buffered.close()
                netConn.disconnect()
                runOnUiThread {
                    Log.d("결과", content.toString())
                }
            }
        }.start()
    }
}