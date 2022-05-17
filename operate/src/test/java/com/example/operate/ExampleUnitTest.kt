package com.example.operate

import org.junit.Assert.assertEquals
import org.junit.Test

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
    fun `연산테스트`(){

        val a = 537919552
        val b = 64

        val result = a.and(64)

        val toHexA = Integer.toHexString(a)
        val toHexB = Integer.toHexString(b)

        println(result == b)

    }

}