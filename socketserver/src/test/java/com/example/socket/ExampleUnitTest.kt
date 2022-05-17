package com.example.socket

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun `배열 테스트`(){

        val byteArray = byteArrayOf(1)

        byteArray.forEach {
            println(it)
        }

//        val toConvertByteToInt = read4BytesFromBuffer(buffer = byteArray,0)

//        println(toConvertByteToInt)

//        println(write4BytesToBuffer(toConvertByteToInt,0,0))

    }


    private fun read4BytesFromBuffer(buffer: ByteArray, offset: Int): Int {
        return (buffer[offset + 3].toInt() shl 24) or
                (buffer[offset + 2].toInt() and 0xff shl 16) or
                (buffer[offset + 1].toInt() and 0xff shl 8) or
                (buffer[offset + 0].toInt() and 0xff)
    }

    private fun write4BytesToBuffer(buffer: ByteArray, offset: Int, data: Int) {
        buffer[offset + 0] = (data shr 0).toByte()
        buffer[offset + 1] = (data shr 8).toByte()
        buffer[offset + 2] = (data shr 16).toByte()
        buffer[offset + 3] = (data shr 24).toByte()
    }


}