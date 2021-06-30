package com.xfs.flashcard.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xfs.flashcard.R
import com.xfs.flashcard.adapter.MyWordAdapter
import com.xfs.flashcard.models.Word

class MyWordActivity : AppCompatActivity() {
    lateinit var myWordListView: ListView
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.my_word_list_view)
        Log.d("SAMPLE", "RUNNNNN")
        val id: String = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)

        db.collection("Favorites").document(id).get().addOnSuccessListener { docs ->
            Log.d("SAMPLE", docs.toString())
            if (docs.exists()) {
                val words = ArrayList<Word>()
                val myWordAdapter = MyWordAdapter(words)
                val ws = docs.data?.get("words") as ArrayList<String>
                db.collection("Words").whereIn(FieldPath.documentId(), ws).get().addOnSuccessListener { docs ->
                    if (!docs.isEmpty) {
                        for (doc in docs) {
                            val data = doc.data
                            val word = Word(
                                doc.id,
                                data["value"] as String,
                                data["mean"] as String,
                                data["spell"] as String,
                                data["image"] as String,
                                data["examples"] as ArrayList<String>
                            )
                            words.add(word)
                        }
                        myWordAdapter.notifyDataSetChanged()
                        myWordListView = findViewById(R.id.my_words_list)
                        myWordListView.adapter = myWordAdapter
                    }
                }
            }
        }
    }
}