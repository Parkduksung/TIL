package com.example.phonenumber

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager

class PhoneNumber(private val context: Context) {
    private val INFO_EMPTY = "EMPTY"

    @SuppressLint("MissingPermission")
    fun getPhoneNumber(): String {
        val telephonyMgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return if (telephonyMgr.line1Number == null || telephonyMgr.line1Number.trim() == "") {
            INFO_EMPTY
        } else {
            var phoneNumber = telephonyMgr.line1Number
            if (isKoreaNetOper(context)) {
                phoneNumber = extractNumber(phoneNumber)
                if (phoneNumber.isNotEmpty() && phoneNumber.substring(0, 2) == "82") {
                    phoneNumber = "0" + phoneNumber.substring(2, phoneNumber.length)
                }
            }
            phoneNumber
        }
    }

    private fun extractNumber(text: String): String? {
        return text.replace("[^\\d]".toRegex(), "") // same regex : [^0-9]
    }

    private fun isKoreaNetOper(context: Context): Boolean {
        return (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).networkOperator?.let { netOper ->
            if (netOper.isEmpty()) return false
            if (netOper.length < 3) return false

            if (netOper.substring(0, 3) == "450") {
                return true
            } else false
        } ?: false
    }
}