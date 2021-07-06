package com.example.til.macaddress

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R
import java.net.NetworkInterface
import java.util.*

class MacAddressActivity : AppCompatActivity() {

    private lateinit var macAddress : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mac_address)


//        findViewById<TextView>(R.id.address_tv).text =getWifiMacAddress(this@MacAddressActivity)


        Log.d("결과", SSAId(this@MacAddressActivity).getAddress())
        Log.d("결과", WideVineId().getAddress())
    }

    private fun getWifiMacAddress(context: Context?): String? {

//        if (::macAddress.isInitialized) return macAddress
        try {
            val interfaceName = "wlan0"
            val interfaces: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())

            for (intf in interfaces) {
                if (!intf.name.equals(interfaceName, ignoreCase = true)) {
                    continue
                }
                val mac = intf.hardwareAddress ?: return "123"
                val buf = java.lang.StringBuilder()
                for (aMac in mac) {
                    buf.append(String.format("%02X:", aMac))
                }
                if (buf.length > 0) {
                    buf.deleteCharAt(buf.length - 1)
                }

                macAddress = buf.toString()
                return macAddress
            }
        } catch (exp: java.lang.Exception) {
            exp.printStackTrace()
            Toast.makeText(this, "여기탐", Toast.LENGTH_LONG).show()
        }

        return if (::macAddress.isInitialized) "11223344" else macAddress
    }
}

