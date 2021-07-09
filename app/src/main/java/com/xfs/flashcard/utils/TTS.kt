package com.xfs.flashcard.utils

import android.app.Activity
import android.speech.tts.TextToSpeech
import android.widget.Toast
import java.util.*

class TTS(
    private val activity: Activity,
    private val message: String
) : TextToSpeech.OnInitListener {

    private val tts: TextToSpeech = TextToSpeech(activity, this)

    override fun onInit(i: Int) {
        if (i == TextToSpeech.SUCCESS) {
            val result: Int = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(activity, "This Language is not supported", Toast.LENGTH_SHORT).show()
            } else {
                speakOut(message)
            }
        } else {
            Toast.makeText(activity, "Initilization Failed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun speakOut(message: String) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
    }

}