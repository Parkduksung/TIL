package com.example.til.keycode

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.til.R

class KeycodeActivity : AppCompatActivity() {


    private val keycodeTextView by lazy { findViewById<TextView>(R.id.keycode_tv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keycode)

        findViewById<EditText>(R.id.input_text_et).addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("결과", keyCode.toString())
        Log.d("결과", event?.keyCode.toString())

        return super.onKeyUp(keyCode, event)
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {

        Log.d("결과", event?.keyCode.toString())

        return super.dispatchKeyEvent(event)
    }


}