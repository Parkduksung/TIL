package com.example.til.network

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.til.R


class NetworkTypeActivity : AppCompatActivity() {


    companion object {

        private const val REQUEST_CODE = 2000
        private const val REQUEST_PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_type)

        checkReadPhoneStatePermission()
    }

    private fun checkReadPhoneStatePermission() {

        if (ContextCompat.checkSelfPermission(this, REQUEST_PERMISSION_READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "requestPermissionState", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(this@NetworkTypeActivity, arrayOf(REQUEST_PERMISSION_READ_PHONE_STATE), REQUEST_CODE)
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            2000 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "requestPermissionState", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "requestPermissionState NO!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onResume() {

        findViewById<TextView>(R.id.network_type_tv).text = getNetwork()

        super.onResume()
    }

    @SuppressLint("NewApi", "MissingPermission")
    private fun getNetworkType(): Int {

        var result = 0
        result = try {
            val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            telephonyManager.dataNetworkType
        } catch (e: Exception) {
            -1
        }

        return result
    }

    @SuppressLint("NewApi", "MissingPermission")
    fun getNetwork(): String {
        val connectivityManager =
                getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return "-"
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return "-"
        when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return "WIFI"
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return "ETHERNET"
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                when (tm.dataNetworkType) {
                    TelephonyManager.NETWORK_TYPE_GPRS,
                    TelephonyManager.NETWORK_TYPE_EDGE,
                    TelephonyManager.NETWORK_TYPE_CDMA,
                    TelephonyManager.NETWORK_TYPE_1xRTT,
                    TelephonyManager.NETWORK_TYPE_IDEN,
                    TelephonyManager.NETWORK_TYPE_GSM -> return "2G"
                    TelephonyManager.NETWORK_TYPE_UMTS,
                    TelephonyManager.NETWORK_TYPE_EVDO_0,
                    TelephonyManager.NETWORK_TYPE_EVDO_A,
                    TelephonyManager.NETWORK_TYPE_HSDPA,
                    TelephonyManager.NETWORK_TYPE_HSUPA,
                    TelephonyManager.NETWORK_TYPE_HSPA,
                    TelephonyManager.NETWORK_TYPE_EVDO_B,
                    TelephonyManager.NETWORK_TYPE_EHRPD,
                    TelephonyManager.NETWORK_TYPE_HSPAP,
                    TelephonyManager.NETWORK_TYPE_TD_SCDMA -> return "3G"
                    TelephonyManager.NETWORK_TYPE_LTE,
                    TelephonyManager.NETWORK_TYPE_IWLAN, 19 -> return "4G"
                    TelephonyManager.NETWORK_TYPE_NR -> return "5G"
                    else -> return "?"
                }
            }
            else -> return "?"
        }
    }
}