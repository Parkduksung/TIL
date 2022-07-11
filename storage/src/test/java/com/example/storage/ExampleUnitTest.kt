package com.example.storage

import android.os.Environment
import android.util.Log
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : BaseTest() {
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
        print("확인")
        assertEquals("확인", outputStreamCaptor.toString())
    }

}