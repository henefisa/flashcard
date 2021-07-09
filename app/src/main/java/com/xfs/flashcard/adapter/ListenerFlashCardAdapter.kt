package com.xfs.flashcard.adapter

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import com.xfs.flashcard.R
import kotlinx.android.synthetic.main.flashcard_content.*
import java.util.*

class ListenerFlashCardAdapter: AppCompatActivity(), TextToSpeech.OnInitListener {

        private var tts: TextToSpeech? = null
        private var imageView: ImageView? = null
        private var textView: TextView? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.flashcard_content)

            imageView = this.text_to_speech_btn
            textView = this.mean

            text_to_speech_btn!!.isEnabled = false;
            tts = TextToSpeech(this, this)

            text_to_speech_btn!!.setOnClickListener { speakOut() }
        }

        override fun onInit(status: Int) {

            if (status == TextToSpeech.SUCCESS) {
                // set US English as language for tts
                val result = tts!!.setLanguage(Locale.US)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS","The Language specified is not supported!")
                } else {
                    text_to_speech_btn!!.isEnabled = true
                }

            } else {
                Log.e("TTS", "Initilization Failed!")
            }

        }

        private fun speakOut() {
            val text = textView!!.text.toString()
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
        }

        public override fun onDestroy() {
            // Shutdown TTS
            if (tts != null) {
                tts!!.stop()
                tts!!.shutdown()
            }
            super.onDestroy()
        }

    }


