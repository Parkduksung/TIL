package com.example.til.macaddress

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaDrm
import android.provider.Settings
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

/**
 * 대체맥주소를 얻는 클래스
 */
class AlternativeMacAddress(private val addressProvider: AddressProvider) {
    fun getAlternativeMacAddress(): String {
        return addressProvider.create().getAddress()
    }
}

/**
 * 각 주소값들을 구현하여 반환
 */
interface Address {
    fun getAddress(): String
}


/**
 * 생성할때 주입받는 ssaId , wideVineId 값에 따라 일시적 날짜 데이터를 반환하거나 hashCode로 변형된 12자리 값을 반환
 */
class AddressProvider(private val ssaId: SSAId, private val wideVineId: WideVineId) {

    fun create(): Address {
        return if (ssaId.getAddress().isEmpty() && wideVineId.getAddress().isEmpty()) {
            TemporaryDateData()
        } else {
            TransformDigitAddress(ConvertHashCode(ssaId.getAddress() + wideVineId.getAddress()).getAddress())
        }
    }
}

/**
 * SSAId 값을 구하는 클래스
 */
class SSAId(private val context: Context) : Address {
    @SuppressLint("HardwareIds")
    override fun getAddress(): String {
        return try {
            val getSSAId =
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            if (!getSSAId.isNullOrEmpty()) {
                getSSAId
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }
}

/**
 * WideVineId 값을 구하는 클래스
 */
class WideVineId : Address {
    override fun getAddress(): String {
        return try {
            val mediaDrm = MediaDrm(UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L))
            val wideVineId = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
            val toEncoder = encodeToString(wideVineId, DEFAULT).trim()

            if (toEncoder.isNotEmpty()) {
                toEncoder
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }
}

/**
 * 일시적인 날짜 데이터 값을 구하는 클래스
 */
class TemporaryDateData : Address {
    @SuppressLint("SimpleDateFormat")
    override fun getAddress(): String {
        return SimpleDateFormat("yyMMddHHmmss").format(Date())
    }
}

/**
 * 해시코드로 변환하는 클래스
 * hashCode 가 음수일때도 존재하므로 절대값처리하여 양수로 반환.
 */
class ConvertHashCode(private val notConvertAddress: String) : Address {
    override fun getAddress(): String {
        return abs(notConvertAddress.hashCode()).toString()
    }
}

/**
 * 12자리 문자열로 변환해주는 클래스
 * ex) 00001111 -> 000011110344
 */

class TransformDigitAddress(private val notTransformAddress: String) : Address {

    override fun getAddress(): String {

        val transformAddressBuilder =
                java.lang.StringBuilder(notTransformAddress)

        val addArray = arrayOf("4", "4", "3", "0")

        // 12자리 숫자를 만들어 주기 위한 로직.

        return if (transformAddressBuilder.length < 12) {
            val convertHashCodeLength = transformAddressBuilder.length
            // 12자리 숫자가 아닌경우 빈공간에 순차적으로 배열의 값을 추가하여 12자리를 만들어준다.
            for (i in convertHashCodeLength..11) {
                transformAddressBuilder.append(addArray[(11 - i) % 4])
            }
            transformAddressBuilder.toString()
        } else {
            // 12자리 숫자 초과인 경우 12자리까지 자른다.
            transformAddressBuilder.substring(0, 12)
        }
    }
}

