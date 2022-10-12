package com.example.hashtable

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
    fun hashmapTest() {

        val hashMapPerson = LinkedHashMap<String, Person>()

        val person1 = Person("박", 30)
        val person2 = Person("덕", 31)
        val person3 = Person("성", 32)
        val person4 = Person("박덕", 33)
        val person5 = Person("박덕성", 33)

        hashMapPerson[person1.name] = person1
        hashMapPerson[person2.name] = person2
        hashMapPerson[person3.name] = person3
        hashMapPerson[person4.name] = person4


        println(hashMapPerson)


        hashMapPerson[person1.name] = person2
        hashMapPerson[person2.name] = person4
        hashMapPerson[person3.name] = person1
        hashMapPerson[person4.name] = person3

        println(hashMapPerson)


        hashMapPerson[person1.name] = person2
        hashMapPerson[person5.name] = person5
        hashMapPerson[person3.name] = person1
        hashMapPerson[person4.name] = person3

        println(hashMapPerson)
    }
}

data class Person(
    val name: String,
    val age: Int
)