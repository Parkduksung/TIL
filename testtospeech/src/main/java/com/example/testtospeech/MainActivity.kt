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
            tts.speakText(this,text)
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
            //todo
        }

        override fun onDone(utteranceId: String) {
            //todo
        }

        override fun onError(utteranceId: String) {
            //todo
        }
    }

    private val onInitListener = TextToSpeech.OnInitListener { status ->
        if (status == TextToSpeech.SUCCESS) {
            tts!!.language = Locale.KOREA
            tts!!.setPitch(1.0f)
            tts!!.setSpeechRate(1.0f)
        } else {
            //todo
        }
    }

    private fun init(context: Context) {
        tts = TextToSpeech(context, onInitListener)
        tts?.setOnUtteranceProgressListener(utteranceProgressListener)
    }

    fun speakText(context: Context, text: String) {
        if (tts == null) {
            init(context)
        }
        val utteranceId = "utteranceId"
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun shutdown() {
        tts?.shutdown()
    }
}