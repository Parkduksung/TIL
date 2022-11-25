package com.example.til

import org.junit.Test

import org.junit.Assert.*
import kotlin.reflect.full.memberProperties

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
    fun `변수 이름 테스트`() {

        println(Sample().getField<String>(""))

//        name.javaClass.fields.forEach {
//            println(it.name)
//        }
//        println(name.javaClass.fields)
    }

}

class Sample {

    var a: String = ""
    var b: String = ""
    var c: String = ""
    var d: String = ""
}

@Throws(IllegalAccessException::class, ClassCastException::class)
fun Any.getField(): MutableMap<String, String?> {
    val hashMap = mutableMapOf<String, String?>()
    this::class.memberProperties.forEach { kCallable ->
        hashMap[kCallable.name.uppercase()] = null
    }
    return hashMap
}