package com.example.storage

import android.widget.Button
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.shadows.ShadowLog
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest : BaseTest() {

    @Before
    override fun setUp() {
        super.setUp()
        ShadowLog.stream = System.out
    }

    @Test
    fun `Robolectric Log Test`() {
        val controller: ActivityController<MainActivity> =
            Robolectric.buildActivity(MainActivity::class.java)

        val activity = controller.create().visible().get()

        val logText = "안녕하세요"

//        println(outputStreamCaptor.toString())

        assertEquals(true, outputStreamCaptor.toString().contains(logText))

    }


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun `파일 경로가 잘 만들어지는지 확인한다`() {

        val fileName = "RemoteViewLog"

        val mockFile = Mockito.mock(File::class.java)

        val documentPath =
            "/documents/"

        Mockito.`when`(mockFile.path)
            .thenReturn(documentPath + fileName)

        val file = File(documentPath, fileName)

        assertEquals(file.path, mockFile.path)

    }

    @Test
    fun `로그가 잘 나오는지 확인한다`() {
        println("확인")
        println("확인")
        assertEquals("확인\n확인\n", outputStreamCaptor.toString())
    }


    @Test
    fun `로그가 길 경우, 내용을 적당한 사이즈에서 잘라 줄바꿈하여 보여지는지 확인한다`() {

        val message1 = mockList.joinToString("").substring(0, 4000)
        val message2 = mockList.joinToString("").substring(4000, 8000)
        val message3 = mockList.joinToString("").substring(8000, 12000)
        val message4 =
            mockList.joinToString("").substring(12000, mockList.joinToString("").lastIndex + 1)


        println(message1)
        println(message2)
        println(message3)
        println(message4)

        val testList = mutableListOf<String>().apply {
            add(message1)
            add(message2)
            add(message3)
            add(message4)
        }

        assertEquals(testList.joinToString("\n") , outputStreamCaptor.toString())
    }


    companion object {

        private val mockList = mutableListOf<String>().apply {
            for (i in 1..4000) {
                add("$i")
            }
        }


    }

}