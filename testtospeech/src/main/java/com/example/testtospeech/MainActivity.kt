package com.example.testtospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.View
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var editText: EditText? = null
    var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.edittext)

        findViewById<View>(R.id.run).setOnClickListener {
            val text = editText?.text.toString()
            speakText(text)
        }
        initTts()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts!!.shutdown()
    }

    private fun initTts() {
        tts = TextToSpeech(applicationContext, this)
        tts!!.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String) {
                Log.i("snowdeer", "[snowdeer] onStart: $utteranceId")
            }

            override fun onDone(utteranceId: String) {
                Log.i("snowdeer", "[snowdeer] onDone: $utteranceId")
            }

            override fun onError(utteranceId: String) {
                Log.i("snowdeer", "[snowdeer] onError: $utteranceId")
            }
        })
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            Log.i("snowdeer", "[snowdeer] TTS is ready.")
            tts!!.language = Locale.KOREA
            tts!!.setPitch(1.0f)
            tts!!.setSpeechRate(1.0f)
        } else {
            Log.w("snowdeer", "[snowdeer] TTS is not ready !!")
        }
    }

    private fun speakText(text: String) {
        val utteranceId = "utteranceId"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }
}