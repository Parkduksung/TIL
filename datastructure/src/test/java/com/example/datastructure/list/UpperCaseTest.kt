package com.example.datastructure.list

import junit.framework.Assert.assertTrue
import org.junit.Test
import java.util.*


class UpperStringArrayTest {

    @Test
    fun test() {

        val names = UpperCaseList()

        names.add("park")
        names.add("duk")
        names.add("sung")

        println(names.joinToString(prefix = "[", postfix = "]", separator = " "))

        assertTrue(names.none { item -> item.all { char -> char.isLowerCase() } })

    }

}


class UpperCaseList : ArrayList<String>() {

    override fun add(element: String): Boolean {
        return super.add(element.uppercase(Locale.getDefault()))
    }

    override fun add(index: Int, element: String) {
        super.add(index, element.uppercase(Locale.getDefault()))
    }

    override fun addAll(elements: Collection<String>): Boolean {
        return super.addAll(elements.map { it.uppercase(Locale.getDefault()) })
    }

    override fun addAll(index: Int, elements: Collection<String>): Boolean {
        return super.addAll(index, elements.map { it.uppercase(Locale.getDefault()) })
    }

    override fun set(index: Int, element: String): String {
        return super.set(index, element.uppercase(Locale.getDefault()))
    }
}