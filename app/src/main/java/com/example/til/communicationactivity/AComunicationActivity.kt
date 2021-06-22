package com.example.til.communicationactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

//A-B-C 일때 C 에 Result 값을 A 에 전달할때 사용.
class ACommunicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicate_a)


        findViewById<Button>(R.id.start_btn).setOnClickListener {
            startBCommunicationActivity()
        }
    }

    private fun startBCommunicationActivity() {

        val intent = Intent(this, BCommunicationActivity::class.java)

        startActivityForResult(intent, REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE) {

            if(resultCode == RESULT_OK){
                Toast.makeText(this, "전달되었다." ,Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val REQUEST_CODE = 1234
    }
}