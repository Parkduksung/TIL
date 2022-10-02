package com.example.datastructure.list

import org.junit.Test
import java.util.*
import kotlin.system.measureTimeMillis

class InsertionTest {

    /**
     * 데이터값이 많지 않을경우에 무의미하지만 데이터가 많아질수록 데이터 삽입, 삭제 성능이 LinkedList > ArrayList
     * ArrayList 는 삽입, 삭제시 Shift 해야 하므로.
     */
    @Test
    fun `LinkedList vs ArrayList Insertion Test`(){


        val isShowAddLog = false

        val linkedList: LinkedList<String> = LinkedList()
        linkedList.add("1")
        linkedList.addAll(arrayOf("2", "3"))
        linkedList.addFirst("Head")
        linkedList.addLast("Last")
        if (isShowAddLog) println(linkedList)

        val addLinkedListTime = measureTimeMillis {
            repeat(100000) {
                linkedList.add(4, "!")
            }
        }


        if (isShowAddLog) println(linkedList)
        println("연결리스트(LinkedList) 데이터 삽입 소요 시간 : $addLinkedListTime")
        println()


        // 선형리스트(ArrayList) 데이터 삽입 시간
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("1")
        arrayList.addAll(arrayOf("2", "3"))
        arrayList.add(0, "Head")
        arrayList.add(arrayList.size, "Last")
        if (isShowAddLog) println(arrayList)

        val addArrayListTime = measureTimeMillis {
            repeat(100000) {
                arrayList.add(4, "!")
            }
        }
        if (isShowAddLog) println(arrayList)
        println("선형리스트(ArrayList) 데이터 삽입 소요 시간 : $addArrayListTime")
    }

    /**
     * LinkedList > MutableList
     * mutableListOf 는 ArrayList 를 반환하기에 위에 결과랑 같다.
     */
    @Test
    fun `LinkedList vs MutableList Insertion Test`(){

        val isShowAddLog = false

        val linkedList: LinkedList<String> = LinkedList()
        linkedList.add("1")
        linkedList.addAll(arrayOf("2", "3"))
        linkedList.addFirst("Head")
        linkedList.addLast("Last")
        if (isShowAddLog) println(linkedList)

        val addLinkedListTime = measureTimeMillis {
            repeat(100000) {
                linkedList.add(4, "!")
            }
        }


        if (isShowAddLog) println(linkedList)
        println("연결리스트(LinkedList) 데이터 삽입 소요 시간 : $addLinkedListTime")
        println()


        // 선형리스트(ArrayList) 데이터 삽입 시간
        val mutableList: MutableList<String> = mutableListOf()
        mutableList.add("1")
        mutableList.addAll(arrayOf("2", "3"))
        mutableList.add(0, "Head")
        mutableList.add(mutableList.size, "Last")
        if (isShowAddLog) println(mutableList)

        val addMutableListTime = measureTimeMillis {
            repeat(100000) {
                mutableList.add(4, "!")
            }
        }
        if (isShowAddLog) println(mutableList)
        println("선형리스트(ArrayList) 데이터 삽입 소요 시간 : $addMutableListTime")
    }

}