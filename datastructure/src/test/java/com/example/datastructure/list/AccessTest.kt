package com.example.datastructure.list

import org.junit.Test
import java.util.*
import kotlin.system.measureTimeMillis

class AccessTest {

    /**
     * 데이터값이 많지 않을경우에 무의미하지만 데이터가 많아질수록 데이터 접근 성능이 ArrayList > LinkedList
     * 복잡도 : ArrayList : O(1) , LinkedList : O(n)
     * ArrayList 는 RandomAccess 상속받고 있어서 O(1)
     */
    @Test
    fun `LinkedList vs ArrayList Access Test`() {


        val isShowAddLog = false

        val linkedList: LinkedList<String> = LinkedList()
        linkedList.add("1")
        linkedList.addAll(arrayOf("2", "3"))
        linkedList.addFirst("Head")
        linkedList.addLast("Last")
        if (isShowAddLog) println(linkedList)

        val accessLinkedListTime = measureTimeMillis {
            repeat(1000000) {
                linkedList[3]
            }
        }


        if (isShowAddLog) println(linkedList)
        println("연결리스트(LinkedList) 데이터 접근 소요 시간 : $accessLinkedListTime")
        println()


        // 선형리스트(ArrayList) 데이터 삽입 시간
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("1")
        arrayList.addAll(arrayOf("2", "3"))
        arrayList.add(0, "Head")
        arrayList.add(arrayList.size, "Last")
        if (isShowAddLog) println(arrayList)

        val accessArrayListTime = measureTimeMillis {
            repeat(1000000) {
                arrayList[3]
            }
        }
        if (isShowAddLog) println(arrayList)
        println("선형리스트(ArrayList) 데이터 접근 소요 시간 : $accessArrayListTime")
    }
}