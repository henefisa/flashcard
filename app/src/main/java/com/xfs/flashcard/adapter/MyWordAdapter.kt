package com.xfs.flashcard.adapter

import android.app.Activity
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xfs.flashcard.R
import com.xfs.flashcard.models.Word
import com.xfs.flashcard.utils.TTS

class MyWordAdapter(w: ArrayList<Word>, a: Activity) : BaseAdapter() {
    private val words: ArrayList<Word> = w
    private val activity = a
    private val db = Firebase.firestore

    override fun getCount(): Int {
        return words.size
    }

    override fun getItem(position: Int): Word {
        return words[position]
    }

    override fun getItemId(position: Int): Long {
        return words[position].id.hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = convertView ?: View.inflate(parent?.context, R.layout.my_word_list_view_item, null)

        val word = getItem(position)
        view.findViewById<TextView>(R.id.word).text = word.value
        view.findViewById<TextView>(R.id.spell).text = word.spell
        view.findViewById<TextView>(R.id.mean).text = word.mean
        view.findViewById<TextView>(R.id.example).text = word.examples[0]
        view.findViewById<ImageButton>(R.id.delete_btn).setOnClickListener {
            val id: String = Settings.Secure.getString(parent?.context?.contentResolver, Settings.Secure.ANDROID_ID)
            db.collection("Favorites").document(id).update("words", FieldValue.arrayRemove(word.id))
                .addOnSuccessListener {
                    words.remove(word)
                    this.notifyDataSetChanged()
                }
            if (words.size === 0) {
                val emptyLayout: LinearLayout? = parent?.findViewById(R.id.empty_layout)
                emptyLayout?.visibility = View.VISIBLE
            }
        }
        view.findViewById<ImageButton>(R.id.speak).setOnClickListener {
            TTS(activity, word.value)
        }
        return view

    }
}