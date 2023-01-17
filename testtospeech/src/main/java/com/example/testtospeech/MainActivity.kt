package com.example.testtospeech

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.View
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {
    var editText: EditText? = null

    private val tts by lazy { TTSUtil }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.edittext)

        findViewById<View>(R.id.run).setOnClickListener {
            val text = editText?.text.toString()
            tts.speakText(applicationContext, text, Locale.ENGLISH)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
    }
}

object TTSUtil {

    private var tts: TextToSpeech? = null

    private val utteranceProgressListener = object : UtteranceProgressListener() {
        override fun onStart(utteranceId: String) {

        }

        override fun onDone(utteranceId: String) {

        }

        override fun onError(utteranceId: String) {

        }
    }


    private val onInitListener: (Locale) -> TextToSpeech.OnInitListener = { language ->
        TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts!!.language = language
                tts!!.setPitch(1.0f)
                tts!!.setSpeechRate(1.0f)
            } else {

            }
        }
    }

    private fun init(context: Context, language: Locale) {
        tts = TextToSpeech(context, onInitListener(language))
        tts?.setOnUtteranceProgressListener(utteranceProgressListener)
    }

    fun speakText(context: Context, text: String, language: Locale) {
        if (tts == null) {
            init(context, language)
        }
        val utteranceId = "utteranceId"
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun shutdown() {
        tts?.shutdown()
    }
}